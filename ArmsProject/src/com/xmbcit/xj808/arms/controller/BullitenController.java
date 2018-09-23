package com.xmbcit.xj808.arms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.services.BullitenImpl;
import com.xmbcit.xj808.arms.entity.Bulliten;
import com.xmbcit.xj808.arms.base.routing.Rout;
import com.xmbcit.xj808.arms.base.routing.RoutController;
import com.xmbcit.xj808.arms.base.routing.auto.Param;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.DataPageImp;
import com.xmbcit.xj808.arms.util.DbUtil;
import com.xmbcit.xj808.arms.util.MessageUtil;
import com.xmbcit.xj808.arms.util.MyDBConn;
import com.xmbcit.xj808.arms.util.SpoceAttributeUtil;
import com.xmbcit.xj808.arms.util.TableCountUtil;

@WebServlet(name = "BullitenController", urlPatterns = "/bulliten/*")
public class BullitenController extends RoutController {

	private static final long serialVersionUID = 1L;
	private MyDBConn conn = new MyDBConn();
	private Bulliten b = new Bulliten();
	private BullitenImpl bImpl = new BullitenImpl(conn);
	

	@Override
	protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {
		req.getRequestDispatcher("query").forward(req, resp);
	}

	/**
	 * 添加公告
	 * 
	 * 
	 * @param resp
	 * 
	 */
	@Rout(url = "add", method = "GET")
	public void add(HttpServletRequest rs, HttpServletResponse resp) throws IOException {
		List<Bulliten> bulliten = bImpl.select(null);
		rs.setAttribute("bulliten", bulliten);
		rs.setAttribute("bnum", TableCountUtil.getBullitenNum());
		resp.sendRedirect("../admin/bulliten/addBulliten.jsp");
	}

	@Rout(url = "add", method = "post")
	public void addBullitenRes(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
			throws IOException {
		String id = DbUtil.getUUID();
		String b_num = TableCountUtil.getBullitenNum();
		String b_title = req.getParameter("b_title");
		String b_content = req.getParameter("b_content");
		String u_num = req.getParameter("n_num");
		Date b_date = new Date();
		Date b_update_time = new Date();
		Date create_time = new Date();
		String org_id = req.getParameter("org_id");
		String u_create_u_num = req.getParameter("u_create_u_num");
		
		b.setId(id);
		b.setBnum(b_num);
		b.setBtitle(b_title);
		b.setBcontent(b_content);
		b.setUnum(u_num);
		b.setBdate(b_date);
		b.setBdate(b_update_time);
		b.setCreateTime(create_time);
		b.setOrgId(org_id);
		b.setuCreateUnum(u_create_u_num);

		if (bImpl.insert(b)) {
			MessageUtil.toast("公告添加成功！", session);
		} else {
			MessageUtil.toast("公告添加失败！", session);
		}
		resp.sendRedirect("query");

	}

	/**
	 * 更新公告
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	@Rout(url = "update", method = "get")
	public void editBulliten(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
			@Param("bnum") String bnum) throws IOException, ServletException {
		String typenum=req.getParameter("bnum");
		bImpl.setJoin("or");
		Bulliten bulliten=new Bulliten();
		bulliten.setBnum(typenum);
		Bulliten bu=CommonUtil.getFirst(bImpl.select(bulliten));
		if(bu==null) {
			MessageUtil.toast("公告不存在", session);
			req.getRequestDispatcher("../admin/bulliten/bullitenManager.jsp").forward(req, resp);
		}else {
			req.setAttribute("bull", bu);
			req.getRequestDispatcher("../admin/bulliten/updateBulliten.jsp").forward(req, resp);
		}
		
		

	}

	@Rout(url = "update", method = "post")
	public void update(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
			throws ServletException, IOException {
		String id=req.getParameter("b_id");
		String bnum = req.getParameter("b_num");
		String btitle = req.getParameter("b_title");
		String bcontent = req.getParameter("b_content");
		String unum = req.getParameter("u_num");
		Date bdate = new Date();
		Date bupdatetime = new Date();
		Date createtime = new Date();
		String orgid = req.getParameter("org_id");
		String ucreateunum = req.getParameter("u_create_u_num");
		
		Bulliten b = bImpl.selectSimpleEntityByNum(id);
		b.setBtitle(btitle);
		b.setBcontent(bcontent);
		b.setbUpdateDate(bupdatetime);
		
		if (bImpl.update(b)) {
			MessageUtil.toast("修改成功！", session);
		} else {
			MessageUtil.toast("修改失败！", session);
		}
		resp.sendRedirect("query");
	}

	@SuppressWarnings("unchecked")
	@Rout(url = "query")
	public void query(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
			@Param("pageindex") String pageIndex, 
			@Param("keyword") String key, 
			@Param("order") String order) {
		try {
			// 获取历史分页
			DataPageImp<Bulliten> bullPage = CommonUtil.convert(session.getAttribute("bullPage"), DataPageImp.class);
			// 获取历史查询模板
			Bulliten type = CommonUtil.convert(session.getAttribute("bullEntity"), Bulliten.class);
			if (bullPage == null) {
				bullPage = new DataPageImp<Bulliten>(null, 0);
				bullPage.setPageSize(20);
			}
			if (type == null) {
				type = new Bulliten();
			}
			// 设置条件参数
			if (key != null) {
				type.setId(key);
				type.setBnum(key);
				type.setBtitle(key);
				type.setBcontent(key);
				session.setAttribute("bullEntity", type);
				session.setAttribute("key", key);
			}
			// 设置页面显示参数
			if (!CommonUtil.isNull(order)) {
				session.setAttribute("order", order);
				bImpl.setOrder(order);
			}
			// 判断页面索引是否为空
			if (!CommonUtil.isNull(pageIndex)) {
				bullPage.setNowPage(CommonUtil.toInt(pageIndex));
			}
			// 设置查询连接条件 and or
			bImpl.setJoin("or");
			// 分页查询
			IPage<Bulliten> page = bImpl.selectPage(bullPage, type);
			// 保存时间
			SpoceAttributeUtil.setAttribute("bullPage", page, session);
			// 页面转发
			req.getRequestDispatcher("../admin/bulliten/bullitenManager.jsp").forward(req, resp);
//			SpoceAttributeUtil.removeAttribute("bullPage",session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

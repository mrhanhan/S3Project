package com.xmbcit.xj808.arms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.services.OrderInfoImpl;
import com.xmbcit.xj808.arms.entity.OrderInfo;
import com.xmbcit.xj808.arms.base.routing.Rout;
import com.xmbcit.xj808.arms.base.routing.RoutController;
import com.xmbcit.xj808.arms.base.routing.auto.Param;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.DataPageImp;
import com.xmbcit.xj808.arms.util.MyDBConn;
import com.xmbcit.xj808.arms.util.SpoceAttributeUtil;

/**
 * 订单控制器
 * 
 * @author ALEX
 *
 */
@WebServlet(name = "OrderInfoController", urlPatterns = "/orderinfo/*")
public class OrderInfoController extends RoutController {
	// 订单操作类
	private MyDBConn conn = new MyDBConn();
	private OrderInfoImpl orderinfoImpl = new OrderInfoImpl(conn);
	private static final long serialVersionUID = 1L;

	@Override
	protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {
		req.getRequestDispatcher("query").forward(req, resp);
	}

	@Rout(url = "query")
	public void queryOrderInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
            @Param("pageindex") String pageIndex,
            @Param("keyword") String key,
            @Param("order") String order) {
		try {
			  //获取历史分页
			  DataPageImp<OrderInfo> orderInfoPage = CommonUtil.convert(session.getAttribute("orderInfoPage"), DataPageImp.class);
			  //获取历史查询模板
			  OrderInfo type = CommonUtil.convert(session.getAttribute("orderinfoEntity"), OrderInfo.class);
			  if (orderInfoPage == null) {
				  orderInfoPage = new DataPageImp<OrderInfo>(null, 0);
				  orderInfoPage.setPageSize(20);
			  }
			  if (type == null) {
				  type = new OrderInfo();
			  }
			  //设置条件参数
			  if (key != null) {
				  type.setId(key);
				  type.setOnum(key);
				  type.setCnum(key);
				  type.setOrgId(key);

			      session.setAttribute("orderinfoEntity",type);
			      session.setAttribute("key", key);
			  }
			  //设置页面显示参数
			  if (!CommonUtil.isNull(order)) {
			      session.setAttribute("order", order);
			      orderinfoImpl.setOrder(order);
			  }
			  //判断页面索引是否为空
			  if (!CommonUtil.isNull(pageIndex)) {
				  orderInfoPage.setNowPage(CommonUtil.toInt(pageIndex));
			  }
			  //设置查询连接条件 and or
			  orderinfoImpl.setJoin("or");
			  //分页查询
			  IPage<OrderInfo> page = orderinfoImpl.selectPage(orderInfoPage, type);
			  //保存时间
			  SpoceAttributeUtil.setAttribute("orderInfoPage", page, session);
			  //页面转发
			  req.getRequestDispatcher("../admin/orderinfo/orderInfoManager.jsp").forward(req, resp);
			  //SpoceAttributeUtil.removeAttribute("typePage",session);
			} catch (Exception e) {
			  e.printStackTrace();
			}
    }

	@Rout(url = "add", method = "get")
	public void addOrderInfo(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {

		resp.sendRedirect(".jsp");
	}

	@Rout(url = "addResult", method = "post")
	public void addResultOrderInfo(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {

		resp.sendRedirect(".jsp");
	}

	@Rout(url = "delete", method = "get")
	public void deleteOrderInfo(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {

		resp.sendRedirect(".jsp");
	}

	@Rout(url = "update", method = "get")
	public void updateOrderInfo(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {

		resp.sendRedirect(".jsp");
	}

	@Rout(url = "updateResult", method = "post")
	public void updateResultOrderInfo(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {

		resp.sendRedirect(".jsp");
	}

}

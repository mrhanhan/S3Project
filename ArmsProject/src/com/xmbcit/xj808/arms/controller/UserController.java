package com.xmbcit.xj808.arms.controller;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.services.UserDaoImp;
import com.xmbcit.xj808.arms.entity.User;
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
import com.xmbcit.xj808.arms.util.TypeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = { "/user/*" ,"/admin"})
public class UserController extends RoutController {

	private static final long serialVersionUID = 1L;

	/* 用户操作类 */
	private MyDBConn myDBConn = new MyDBConn();

	private UserDaoImp dao = new UserDaoImp(myDBConn);
	@Rout(url = "admin")
	public void admin(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("index.jsp");
	}

	@Override
	protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {
		resp.sendRedirect("../index.jsp");
	}

	/** 用户登入 */
	@Rout(url = "login", method = "post")
	public void login(HttpServletRequest req, @Param("n_username") String n_username,
			@Param("n_userpwd") String n_userpwd, HttpServletResponse resp, HttpSession session)
			throws ServletException, IOException {
		User u = new User();

		u.setUaccout(n_username);
		u.setUpwd(n_userpwd);
		dao.setJoin("and");
		List<User> us = dao.select(u);
		if (us.isEmpty()) {
			MessageUtil.toast("登陆失败！不存在用户！", session);
			// MessageUtil.alert("登陆失败！不存在用户！", session);
			resp.sendRedirect("../index.jsp");
		} else {
			session.setAttribute("LOGIN_USER", us.get(0));
			MessageUtil.toast("登陆成功！", session);
			resp.sendRedirect("../admin/home.jsp");
		}

	}

	/** 用户退出 */
	@Rout(url = "logout")
	public void logout(HttpServletRequest req, HttpSession session, HttpServletResponse resp)
			throws ServletException, IOException {
		User u = CommonUtil.convert(session.getAttribute("LOGIN_USER"), User.class);
		session.removeAttribute("LOGIN_USER");
		session.invalidate();
		session = req.getSession();
		MessageUtil.toast("用户【" + u.getUname() + "】退出登陆！", session);
		resp.sendRedirect("../index.jsp");
	}

	/** 查询用户 */
	@SuppressWarnings("unchecked")
	@Rout(url = "query")
	public void query(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
			@Param("pageindex") String pageIndex, @Param("keyword") String key, @Param("order") String order)
			throws ServletException, IOException {

		// 获取历史分页
		DataPageImp<User> userPage = CommonUtil.convert(session.getAttribute("userPage"), DataPageImp.class);

		// 获取历史查询模板
		User user = CommonUtil.convert(session.getAttribute("userEntity"), User.class);
		if (userPage == null) {
			userPage = new DataPageImp<User>(null, 0);
			userPage.setPageSize(20);
		}
		if (user == null) {
			user = new User();
		}
		// 设置条件参数
		if (key != null) {
			user.setId(key);
			user.setUnum(key);
			user.setUname(key);
			user.setUaccout(key);
			user.setUauto(key);
			session.setAttribute("userEntity", user);
			session.setAttribute("key", key);
		}

		// 设置页面显示参数
		if (!CommonUtil.isNull(order)) {
			session.setAttribute("order", order);
			dao.setOrder(order);
		}
		// 判断页面索引是否为空

		if (!CommonUtil.isNull(pageIndex)) {
			userPage.setNowPage(CommonUtil.toInt(pageIndex));
		}
		// 设置查询连接条件 and or
		dao.setJoin("or");
		// 分页查询
		IPage<User> page = dao.selectPage(userPage, user);
		// 保存时间
		SpoceAttributeUtil.setAttribute("userPage", page, session);
		// 页面转发
		req.getRequestDispatcher("../admin/user/userManager.jsp").forward(req, resp);
		//SpoceAttributeUtil.removeAttribute("userPage", session);
	}

	/**
	 * 设置查询状态
	 *
	 * @param req
	 * @param session
	 * @param state
	 */
	@Rout(url = "queryState")
	public void setQuery(HttpServletRequest req, HttpSession session, HttpServletResponse resp,
			@Param("state") String state) throws ServletException, IOException {
		User user = new User();
		if (state != null) {
			user.setUauto(state);
			session.removeAttribute("key");
		}
		session.setAttribute("userEntity", user);
		resp.sendRedirect("query");
	}

	/** 增加用户 get */
	@Rout(url = "add", method = "get")
	public void addUI(HttpServletRequest rs, HttpServletResponse resp) throws ServletException, IOException {
		List<User> User = dao.select(null);
		rs.setAttribute("User", User);
		rs.setAttribute("userNum", TableCountUtil.getUserNum());
		rs.getRequestDispatcher("../admin/user/addUser.jsp").forward(rs, resp);
	}

	/** 增加用户 */
	@Rout(url = "add", method = "post")
	public void login(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
			throws ServletException, IOException, SmartUploadException {
		// 1 新建上传下载类
		SmartUpload sup = new SmartUpload();
		// 2 初始化servlet参数信息
		try {
			sup.initialize(getServletConfig(), req, resp);
		} catch (ServletException e) {

			e.printStackTrace();
		}

		// 3 获取上传的文件
		try {
			sup.upload();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 4 新建流的Request对象
		Request request = sup.getRequest();
		// 5 获取对应文件
		File f = sup.getFiles().getFile(0);
		String page = getServletContext().getRealPath("/");
		String exm = f.getFileExt();
		page = page + "/images/user";
		String id = DbUtil.getUUID(); // id
		java.io.File die = new java.io.File(page);
		if(!die.isDirectory()){
			die.mkdirs();
		}
		String path = page + "\\" + id + "." + exm;
		String filename = id + "." + exm;

		if (exm.isEmpty()) {
			filename = "default.png";
		} else {
			die = new java.io.File(path);
			if(die.isFile()){
				die.delete();
			}
			f.saveAs(path);
		}
		String u_num = TableCountUtil.getUserNum();// request.getParameter("u_num"); // 管理员编号
		String u_name = request.getParameter("u_name"); // 管理员名称
		String u_auto = request.getParameter("u_auto"); // 管理员权限
		String u_account = request.getParameter("u_account"); // 管理员账号
		String u_pwd = request.getParameter("u_pwd"); // 管理员密码
		String u_pwds = request.getParameter("u_pwds"); // 管理员确认密码
		if (u_pwd != null) {
			if (!u_pwd.equals(u_pwds)) {
				MessageUtil.toast("用户密码不一致！", session);
				resp.sendRedirect("../admin/user/addUser.jsp");
				return;
			}
		}
		Date create_time = new Date(); // 时间

		String org_id = "厦门";//
		String u_create_u_num = "123"; //

		User u = new User();
		UserDaoImp dao = new UserDaoImp(myDBConn);
		u.setUimg(filename);
		u.setId(id);
		u.setUnum(u_num);
		u.setUname(u_name);
		u.setUauto(u_auto);
		u.setUaccout(u_account);
		u.setCreateTime(create_time);
		u.setUpwd(u_pwd);

		u.setOrgId(org_id);
		u.setuCreateUnum(u_create_u_num);

		if (dao.insert(u)) {
			MessageUtil.toast("用户添加成功！", session);
			resp.sendRedirect("query");
		} else {
			MessageUtil.toast("用户添加失败！", session);
		}
		

	}

	/** 更新用户 get */
	@Rout(url = "update", method = "get")
	public void updateJsp(HttpServletRequest req, HttpSession session, HttpServletResponse resp,
			@Param("unum") String unum) throws IOException, ServletException {
		if (CommonUtil.isNull(unum)) {
			MessageUtil.toast("请选择需要修改的用户！", session);
			resp.sendRedirect("query");
			return;
		}
		// 设置所有类型
		req.setAttribute("user", dao.select(null));
		User u = new User();
		u.setUnum(unum);
		u = CommonUtil.getFirst(dao.select(u));
		if (CommonUtil.isNull(u)) {
			MessageUtil.toast("请选择需要修改的用户！", session);
			resp.sendRedirect("query");
			return;
		}

		req.setAttribute("user", u);

		req.getRequestDispatcher("../admin/user/updateUser.jsp").forward(req, resp);

	}

	/** 更新用户 */
	@Rout(url = "update", method = "post")
	public void update(HttpServletRequest req, HttpSession session, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {
		// 1 新建上传下载类
		SmartUpload sup = new SmartUpload();
		// 2 初始化servlet参数信息
		try {
			sup.initialize(getServletConfig(), req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}

		// 3 获取上传的文件
		try {
			sup.upload();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 4 新建流的Request对象
		Request request = sup.getRequest();
		// 5 获取对应文件
		File f = sup.getFiles().getFile(0);

		String exm = f.getFileExt();

		String id = request.getParameter("u_id");
		String fileName = id + "." + exm;

		String u_num = request.getParameter("u_num"); // 管理员编号
		String u_name = request.getParameter("u_name"); // 管理员名称
		String u_auto = request.getParameter("u_auto"); // 管理员权限
		String u_account = request.getParameter("u_account"); // 管理员账号
		String upwd1 = request.getParameter("u_pwd1");// 管理员旧密码
		String upwd2 = request.getParameter("u_pwd2"); // 管理员密码
		String upwd3 = request.getParameter("u_pwd3"); // 管理员确认密码
		
		String org_id = "厦门";//
		String u_create_u_num = "123"; //

		User u = TypeUtil.convert(session.getAttribute("LOGIN_USER"), User.class);
		User user = dao.selectSimpleEntityByNum(id);
		user.setUnum(u_num);
		user.setUname(u_name);
		user.setUauto(u_auto);
		user.setUaccout(u_account);
		if (upwd1 != null) {
			if (upwd1.equals(user.getUpwd())) {
				if (!upwd2.equals(upwd3)) {
					MessageUtil.toast("新密码不一致！", session);
					resp.sendRedirect("update?unum="+user.getUnum());
					return;
				} else {
					user.setUpwd(upwd2);
				}

			} else {
				MessageUtil.toast("用户旧密码输入错误！", session);
				resp.sendRedirect("update?unum="+user.getUnum());
				return;
			}
		}
		user.setOrgId(org_id);
		user.setuCreateUnum(u_create_u_num);

		if (!"".equals(exm))
			user.setUimg(fileName);

		if (dao.update(user)) {
			if (!"".equals(exm)) {
				String path = getServletContext().getRealPath("/") + "images/user/" + user.getUimg();
				java.io.File fs = new java.io.File(getServletContext().getRealPath("/") + "images/user/");
				if(!fs.isDirectory()){
					fs.mkdirs();
				}
				 fs = new java.io.File(path);
				if (fs.exists()) {
					fs.delete();
				}
				try {
					System.out.println(path);
					f.saveAs(path, SmartUpload.SAVE_PHYSICAL); // 2进制的方式存储图片
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			MessageUtil.toast("修改用户信息成功！", session);
			resp.sendRedirect("query");
		} else {
			MessageUtil.toast("修改用户信息失败！", session);
			resp.sendRedirect("update?unum="+user.getUnum());
		}


	}

	/** 更新用户 */
	@Rout(url = "updatePass", method = "post",isUpload=true)
	public void updatePass(HttpServletRequest req, HttpSession session, HttpServletResponse resp,Request rest)
			throws ServletException, IOException {
		User u = TypeUtil.convert(session.getAttribute("LOGIN_USER"), User.class);
		String u_account = rest.getParameter("u_account"); // 管理员账号
		String upwd1 = rest.getParameter("u_pwd1");// 管理员旧密码
		String upwd2 = rest.getParameter("u_pwd2"); // 管理员密码
		String upwd3 = rest.getParameter("u_pwd3"); // 管理员确认密码
		u.setUaccout(u_account);
		if (upwd1 != null) {
			if (upwd1.equals(u.getUpwd())) {
				if (!upwd2.equals(upwd3)) {
					MessageUtil.toast("新密码不一致！", session);
					resp.sendRedirect("./admin/user/updatePass.jsp");
					return;
				} else {
					u.setUpwd(upwd2);
					if (dao.update(u)) {
						MessageUtil.toast("修改用户信息成功！", session);
					} else {
						MessageUtil.toast("修改用户信息失败！", session);
					}
					resp.sendRedirect("query");
				}

			} else {
				MessageUtil.toast("用户密码不一致！", session);
				resp.sendRedirect("./admin/user/updatePass.jsp");
				return;
			}
		}

	}
}

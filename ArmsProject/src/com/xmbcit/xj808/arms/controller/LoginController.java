package com.xmbcit.xj808.arms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUploadException;
import com.xmbcit.xj808.arms.services.Customerimp;
import com.xmbcit.xj808.arms.entity.Customer;
import com.xmbcit.xj808.arms.base.routing.Rout;
import com.xmbcit.xj808.arms.base.routing.RoutController;
import com.xmbcit.xj808.arms.base.routing.auto.Param;
import com.xmbcit.xj808.arms.util.*;

@WebServlet(name = "LoginController", urlPatterns = {"/useroperation/*"})
public class LoginController extends RoutController {

    private static final long serialVersionUID = 1L;
    private MyDBConn conn = new MyDBConn();
    private Customerimp cusDao = new Customerimp(conn);

    @Override
    protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

    @Rout(url = "login", method = "GET")
    public void customerLogin(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("../shopping/cus/login.jsp");
    }

    /**
     * 客户登陆
     *
     * @param req
     * @param resp
     * @param session
     */
    @Rout(url = "login", method = "POST")
    public void customerLogin(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
                              @Param("acc") String acc, // 客户账号
                              @Param("pwd") String pwd// 客户密码
    ) throws IOException, ServletException {
        if (CommonUtil.isNull(acc) || CommonUtil.isNull(pwd)) {
            MessageUtil.toast("请输入账号和密码！", session);
            resp.sendRedirect("../useroperation/login");
            return;
        } else {
            Customer c = new Customer();
            c.setCaccount(acc);
            c.setCpwd(pwd);
            cusDao.setJoin("and");//精准匹配
            c = CommonUtil.getFirst(cusDao.select(c));//查询客户
            if (c != null) {
                session.setAttribute("LOGIN_CUS", c);
                resp.sendRedirect("../shop");
            } else {
                MessageUtil.toast("客户账号或者密码错误！", session);
                resp.sendRedirect("login?acc="+acc+"&pwd"+pwd);
            }
            ;
        }


    }

    @Rout(url = "add", method = "POST", isUpload = true)
    public void customerLogin(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Request request,
                              com.jspsmart.upload.Files files) throws ServletException, IOException, SmartUploadException {

        String page = getServletContext().getRealPath("/")+"images/customer";
        String filename = "default.png";
        String id = DbUtil.getUUID(); // id
        java.io.File die = new java.io.File(page);
        if (!die.isDirectory()) {
            die.mkdirs();
        }

        String cnum = TableCountUtil.getCustomerNum();// 客户编号
        String caccount = request.getParameter("u_user"); // 客户账号
        String name = request.getParameter("u_name");// 客户名称
        String pwd = request.getParameter("u_pwd");// 客户密码
        String tele = request.getParameter("u_tele");// 确认密码

        String caddress = request.getParameter("u_add");// 客户地址

        String cemail = request.getParameter("u_email");// 客户邮箱
        String orgid = "厦门";// 店铺地址
        String ccreateunum = "01"; //
        Date createtime = new Date(); // 注册时间

        Customer cu = new Customer();
        if (!CommonUtil.isNull(caccount) || !CommonUtil.isNull(name) || !CommonUtil.isNull(pwd)) {
            cu.setId(id);
            cu.setCnum(cnum);
            cu.setCname(name);
            cu.setCimg(filename);
            cu.setCaddress(caddress);
            cu.setCtelephone(tele);
            cu.setCaccount(caccount);
            cu.setCpwd(pwd);
            cu.setCemail(cemail);
            cu.setCreateTime(createtime);
            cu.setOrgId(orgid);
            cu.setuCreateUnum(ccreateunum);
            if (cusDao.insert(cu)) {
                MessageUtil.toast("注册成功！", session);
                resp.sendRedirect("login");
            } else {
                MessageUtil.toast("注册失败！", session);
                resp.sendRedirect("login");
            }

        } else {
            MessageUtil.toast("请填写完整信息！", session);
            resp.sendRedirect("login");
        }

    }

    /**
     * 客户推出登陆
     *
     * @param resp
     * @param session
     */
    @Rout(url = "logout")
    public void customerLogin(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        session.removeAttribute("LOGIN_CUS");
        session.invalidate();
        MessageUtil.toast("退出成功！",req.getSession());
        resp.sendRedirect("/shop");
    }

    @Rout(url = "customer")
    public void customer(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException, ServletException {
        Customer login = TypeUtil.convert(session.getAttribute("LOGIN_CUS"), Customer.class);
        if (login == null) {
            MessageUtil.toast("请先进行登陆！", session);
            //转发
            resp.sendRedirect("login");
        } else {
            req.getRequestDispatcher("../shopping/home/personal.jsp").forward(req, resp);
        }

    }


}

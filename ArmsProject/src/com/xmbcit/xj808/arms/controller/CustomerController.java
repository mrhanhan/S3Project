package com.xmbcit.xj808.arms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.services.Customerimp;
import com.xmbcit.xj808.arms.entity.Customer;
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

@WebServlet(name = "CustomerController", urlPatterns = {"/customer/*"})
public class CustomerController extends RoutController {

    private static final long serialVersionUID = 1L;
    private MyDBConn conn = new MyDBConn();
    private Customerimp imp = new Customerimp(conn);

    @Override
    protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
            throws ServletException, IOException {
        req.getRequestDispatcher("query").forward(req, resp);
        ;

    }

    @Rout(url = "add", method = "GET")
    public void addUi(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("../admin/customer/addCus.jsp");
    }

    @Rout(url = "add", method = "POST")
    public void addCustomer(HttpServletRequest reqo, HttpServletResponse resp, HttpSession session) {
        SmartUpload sup = new SmartUpload();
        try {
            sup.initialize(getServletConfig(), reqo, resp);
        } catch (ServletException e) {

            e.printStackTrace();
        }

        try {
            sup.upload();
        } catch (SmartUploadException | IOException | ServletException e) {

            e.printStackTrace();
        }
        try {
            Request req = sup.getRequest();
            Files fs = sup.getFiles();
            File f = null;
            String fileName = "";
            if (fs.getCount() > 0) {
                f = fs.getFile(0);
            }
            String id = DbUtil.getUUID(); // id
            String cnum = TableCountUtil.getCustomerNum();// 客户编号

            if (CommonUtil.isNull(f)) {
                fileName = "default.png";
            } else {
                fileName = id + "." + f.getFileExt();
            }
            String cname = req.getParameter("u_name");// 客户名称
            System.out.println(cname);
            String caddress = req.getParameter("n_caddress");// 客户地址
            System.out.println(caddress);
            String ctelephone = req.getParameter("n_ctelephone");// 客户联系方式
            System.out.println(ctelephone);
            String caccount = req.getParameter("n_caccount");// 客户账号
            System.out.println(caccount);
            String cpwd = req.getParameter("n_pwd");// 客户密码
            System.out.println(cpwd);
            String cpwds = req.getParameter("n_pwds");// 客户确认密码
            if (cpwd != null) {
                if (!cpwd.equals(cpwds)) {
                    MessageUtil.toast("客户密码不一致！", session);
                    resp.sendRedirect("../admin/customer/addCus.jsp");
                    return;
                }
            }
            String cemail = req.getParameter("n_email");// 客户邮箱
            System.out.println(cemail);
            Date createtime = new Date(); // 时间
            System.out.println(createtime);
            String orgid = "厦门";
            System.out.println(orgid);
            String ccreateunum = "01";
            System.out.println(ccreateunum);

            Customer cu = new Customer();
            Customerimp imp = new Customerimp(conn);
            cu.setId(id);
            cu.setCnum(cnum);
            cu.setCimg(fileName);
            cu.setCname(cname);
            cu.setCaddress(caddress);
            cu.setCtelephone(ctelephone);
            cu.setCaccount(caccount);
            cu.setCpwd(cpwd);
            cu.setCemail(cemail);
            cu.setCreateTime(createtime);
            cu.setOrgId(orgid);
            cu.setuCreateUnum(ccreateunum);
            if (imp.insert(cu)) {
                MessageUtil.toast("客户添加成功！", session);
                resp.sendRedirect("../admin/customer/addCus.jsp");
            } else {
                MessageUtil.toast("客户添加失败！", session);
            }
            String path = getServletContext().getRealPath("/")+"images/Customer/" + fileName;
            java.io.File ff = new java.io.File(getServletContext().getRealPath("/") + "images/Customer/");
            if(!ff.exists() || !ff.isDirectory()){
                ff.mkdirs();
            }
            try {
                if (f != null) {
                    f.saveAs(path, SmartUpload.SAVE_PHYSICAL); // 2进制的方式存储图片
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Rout(url = "query")
    public void query(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
                      @Param("pageindex") String pageIndex, @Param("keyword") String key, @Param("order") String order) {
        try {

            // 获取历史分页
            DataPageImp<Customer> cusPage = CommonUtil.convert(session.getAttribute("cusPage"), DataPageImp.class);
            // 获取历史查询模板
            Customer type = CommonUtil.convert(session.getAttribute("cusEntity"), Customer.class);
            if (cusPage == null) {
                cusPage = new DataPageImp<Customer>(null, 0);
                cusPage.setPageSize(20);
            }
            if (type == null) {
                type = new Customer();
            }
            // 设置条件参数
            if (key != null) {
                type.setId(key);
                type.setCnum(key);
                type.setCimg(key);
                type.setCname(key);
                type.setCaddress(key);
                type.setCtelephone(key);
                type.setCaccount(key);
                type.setCpwd(key);
                type.setCemail(key);
                type.setOrgId(key);
                type.setuCreateUnum(key);
                session.setAttribute("cusEntity", type);
                session.setAttribute("key", key);
            }
            // 设置页面显示参数
            if (!CommonUtil.isNull(order)) {
                session.setAttribute("order", order);
                imp.setOrder(order);
            }
            // 判断页面索引是否为空
            if (!CommonUtil.isNull(pageIndex)) {
                cusPage.setNowPage(CommonUtil.toInt(pageIndex));
            }
            // 设置查询连接条件 and or
            imp.setJoin("or");
            // 分页查询
            IPage<Customer> page = imp.selectPage(cusPage, type);
            System.out.println(page);
            // 保存时间
            SpoceAttributeUtil.setAttribute("cusPage", page, session);
            // 页面转发
            req.getRequestDispatcher("../admin/customer/customerManager.jsp").forward(req, resp);
            // SpoceAttributeUtil.removeAttribute("typePage",session);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改
     *
     * @param resp
     * @param session
     * @throws ServletException
     * @throws IOException
     */
    @Rout(url = "update", method = "post")
    public void upde(HttpServletRequest reqo, HttpServletResponse resp, HttpSession session)
            throws ServletException, IOException {
        // 1 新建上传下载类
        SmartUpload sup = new SmartUpload();
        // 2 初始化servlet参数信息
        try {
            sup.initialize(getServletConfig(), reqo, resp);
        } catch (ServletException e) {

            e.printStackTrace();
        }
        // 3 获取上传的文件
        try {
            sup.upload();
        } catch (SmartUploadException | IOException | ServletException e) {

            e.printStackTrace();
        }
        // 4 新建流的Request对象
        Request req = sup.getRequest();
        // 5 获取对应文件
        File f = sup.getFiles().getFile(0);
        String exm = f.getFileExt();

        String id = req.getParameter("c_id");
        String fileName = id + "." + exm;
        String cnum = TableCountUtil.getCustomerNum();// 客户编号

        String cname = req.getParameter("u_name");// 客户名称
        System.out.println(cname);
        String caddress = req.getParameter("n_caddress");// 客户地址
        System.out.println(caddress);
        String ctelephone = req.getParameter("n_ctelephone");// 客户联系方式
        System.out.println(ctelephone);
        String caccount = req.getParameter("n_caccount");// 客户账号
        System.out.println(caccount);
        String cpwd = req.getParameter("n_pwd");// 客户密码
        System.out.println(cpwd);
        String cpwds = req.getParameter("n_pwds");// 客户确认密码
        if (cpwd != null) {
            if (!cpwd.equals(cpwds)) {
                MessageUtil.toast("客户密码不一致！", session);
                resp.sendRedirect("../admin/customer/updateCus.jsp");
                return;
            }
        }
        String cemail = req.getParameter("n_email");// 客户邮箱
        System.out.println(cemail);
        Date createtime = new Date(); // 时间
        System.out.println(createtime);
        String orgid = "厦门";
        System.out.println(orgid);
        String ccreateunum = "01";
        System.out.println(ccreateunum);

        Customer cu = imp.selectSimpleEntityByNum(id);

        cu.setCnum(cnum);
        cu.setCname(cname);
        cu.setCaddress(caddress);
        cu.setCtelephone(ctelephone);
        cu.setCaccount(caccount);
        cu.setCpwd(cpwd);
        cu.setCemail(cemail);
        cu.setCreateTime(createtime);
        cu.setOrgId(orgid);
        cu.setuCreateUnum(ccreateunum);
        if (!"".equals(exm))
            cu.setCimg(fileName);

        if (imp.update(cu)) {
            if (!"".equals(exm)) {
                String path = getServletContext().getRealPath("/") + "images/Customer/" + cu.getCimg();
                java.io.File fs = new java.io.File(getServletContext().getRealPath("/") + "images/Customer/");
                if(!fs.isDirectory()){
                    fs.mkdirs();
                }
                 fs = new java.io.File(path);
                if (fs.exists()) {
                    fs.delete();
                }
                try {

                    f.saveAs(path, SmartUpload.SAVE_PHYSICAL); // 2进制的方式存储图片
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            MessageUtil.toast("修改客户信息成功！", session);
        } else {
            MessageUtil.toast("修改客户信息失败！", session);
        }
        resp.sendRedirect("query");
    }   
    /**
     * 修改个人信息
     *
     * @param resp
     * @param session
     * @throws ServletException
     * @throws IOException
     */
    @Rout(url = "updatepersonal", method = "post")
    public void updepersonal(HttpServletRequest reqo, HttpServletResponse resp, HttpSession session)
            throws ServletException, IOException {
        // 1 新建上传下载类
        SmartUpload sup = new SmartUpload();
        // 2 初始化servlet参数信息
        try {
            sup.initialize(getServletConfig(), reqo, resp);
        } catch (ServletException e) {

            e.printStackTrace();
        }
        // 3 获取上传的文件
        try {
            sup.upload();
        } catch (SmartUploadException | IOException | ServletException e) {

            e.printStackTrace();
        }
        // 4 新建流的Request对象
        Request req = sup.getRequest();
        // 5 获取对应文件
        File f = sup.getFiles().getFile(0);
        String exm = f.getFileExt();

        String id = req.getParameter("c_id");
        String fileName = id + "." + exm;
        String cnum = TableCountUtil.getCustomerNum();// 客户编号

        String cname = req.getParameter("u_name");// 客户名称
        System.out.println(cname);
        String caddress = req.getParameter("n_caddress");// 客户地址
        System.out.println(caddress);
        String ctelephone = req.getParameter("n_ctelephone");// 客户联系方式
        System.out.println(ctelephone);
        String caccount = req.getParameter("n_caccount");// 客户账号
        System.out.println(caccount);
        String cpwd = req.getParameter("n_pwd");// 客户密码
        System.out.println(cpwd);
        String cpwds = req.getParameter("n_pwds");// 客户确认密码
        if (cpwd != null) {
            if (!cpwd.equals(cpwds)) {
                MessageUtil.toast("客户密码不一致！", session);
                
                resp.sendRedirect("");//进入页面！！！！！！！！！！！！！
                return;
            }
        }
        String cemail = req.getParameter("n_email");// 客户邮箱
        System.out.println(cemail);
        Date createtime = new Date(); // 时间
        System.out.println(createtime);
        String orgid = "厦门";
        System.out.println(orgid);
        String ccreateunum = "01";
        System.out.println(ccreateunum);

        Customer cu = imp.selectSimpleEntityByNum(id);

        cu.setCnum(cnum);
        cu.setCname(cname);
        cu.setCaddress(caddress);
        cu.setCtelephone(ctelephone);
        cu.setCaccount(caccount);
        cu.setCpwd(cpwd);
        cu.setCemail(cemail);
        cu.setCreateTime(createtime);
        cu.setOrgId(orgid);
        cu.setuCreateUnum(ccreateunum);
        if (!"".equals(exm))
            cu.setCimg(fileName);

        if (imp.update(cu)) {
            if (!"".equals(exm)) {
                String path = getServletContext().getRealPath("/") + "images/Customer/" + cu.getCimg();
                java.io.File fs = new java.io.File(getServletContext().getRealPath("/") + "images/Customer/");
                if(!fs.isDirectory()){
                    fs.mkdirs();
                }
                 fs = new java.io.File(path);
                if (fs.exists()) {
                    fs.delete();
                }
                try {

                    f.saveAs(path, SmartUpload.SAVE_PHYSICAL); // 2进制的方式存储图片
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            MessageUtil.toast("个人信息修改成功！", session);
        } else {
            MessageUtil.toast("个人信息修改失败！", session);
        }
        resp.sendRedirect("query");
    } 
    @Rout(url = "update", method = "GET")
    public void update(HttpServletRequest req, HttpSession session, HttpServletResponse resp)
            throws IOException, ServletException {
        String typenum = req.getParameter("gnum");
        imp.setJoin("or");
        Customer customer = new Customer();
        customer.setCnum(typenum);
        Customer gt = CommonUtil.getFirst(imp.select(customer));
        if (gt == null) {
            MessageUtil.toast("选择修改用户不存在！", session);
            req.getRequestDispatcher("../admin/customer/customerManager.jsp").forward(req, resp);
        } else {
            req.setAttribute("customer", gt);
            req.getRequestDispatcher("../admin/customer/updateCus.jsp").forward(req, resp);
        }
    }



    @Rout(url = "login",method = "get")
    public void customerLogin(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        resp.sendRedirect("../shopping/cus/login.jsp");
    }
    /**
     * 客户推出登陆
     *
     * @param resp
     * @param session
     */
    @Rout(url = "logout")
    public void customerLogin( HttpServletResponse resp, HttpSession session) throws IOException {
        session.removeAttribute("LOGIN_CUS");
        session.invalidate();
        resp.sendRedirect("../shop");
    }

}

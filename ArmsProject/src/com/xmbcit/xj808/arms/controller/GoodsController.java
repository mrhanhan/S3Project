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

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.xmbcit.xj808.arms.api.dao.IPage;
import com.xmbcit.xj808.arms.services.GoodsImp;
import com.xmbcit.xj808.arms.services.GoodsTypeimp;
import com.xmbcit.xj808.arms.services.ImagesImpl;
import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.GoodsType;
import com.xmbcit.xj808.arms.entity.Images;
import com.xmbcit.xj808.arms.entity.User;
import com.xmbcit.xj808.arms.base.routing.Rout;
import com.xmbcit.xj808.arms.base.routing.RoutController;
import com.xmbcit.xj808.arms.base.routing.auto.Param;
import com.xmbcit.xj808.arms.util.*;

@WebServlet(name = "GoodsController", urlPatterns = {"/goods/*"})
public class GoodsController extends RoutController {

    private static final long serialVersionUID = 1L;
    private MyDBConn conn = new MyDBConn();
    private GoodsImp goodsDao = new GoodsImp(conn);
    private GoodsTypeimp typeDao = new GoodsTypeimp(conn);
    private ImagesImpl images = new ImagesImpl(conn);
    @Override
    protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
            throws ServletException, IOException {
        resp.sendRedirect("../index.jsp");
    }

    /**
     *
     * @param rs
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Rout(url = "add", method = "get")
    public void addUI(HttpServletRequest rs, HttpServletResponse resp) throws ServletException, IOException {
        List<GoodsType> types = typeDao.select(null);
        rs.setAttribute("types", types);
        rs.setAttribute("goodsNum", TableCountUtil.getGoodsNum());
        rs.getRequestDispatcher("../admin/goods/addGoods.jsp").forward(rs, resp);
    }

    /**
     *
     * @param req
     * @param resp
     * @param session
     * @param request
     * @param files
     * @throws ServletException
     * @throws IOException
     * @throws SmartUploadException
     */
    @Rout(url = "add", method = "post",isUpload = true)
    public void add(HttpServletRequest req, HttpServletResponse resp, HttpSession session, Request request, com.jspsmart.upload.Files files)
            throws ServletException, IOException, SmartUploadException {

        File f = files.getFile(0);
        String page = getServletContext().getRealPath("/");
        String exm = f.getFileExt();
        page = page + "/images/goodspic";
        String id = DbUtil.getUUID(); // id
        java.io.File die = new java.io.File(page);
        if(!die.isDirectory()){
            die.mkdirs();
        }
        String root = page;
        String path = page + "\\" + id + "." + exm;
        String filename = id + "." + exm;
        if (exm.isEmpty()) {
            filename = "default.jpg";
        } else {
            die = new java.io.File(path);
            if(die.isFile()){
                die.delete();
            }
            f.saveAs(path);
        }


        String g_num = request.getParameter("g_num"); // 编号
        String t_num = request.getParameter("t_num"); // 类型编号
        String g_name = request.getParameter("g_name"); // 商品名称
        String g_Price = request.getParameter("g_price"); // 商品价格
        double g_price = CommonUtil.toDouble(g_Price);
        String g_Discount = request.getParameter("g_discount"); // 商品折扣
        float g_discount = CommonUtil.toFloat(g_Discount);
        String g_state = request.getParameter("g_state"); // 商品状态
        String g_isdiscount = request.getParameter("g_isdiscount"); // 是否折扣（是/否）
        String g_img = path;
        String g_isnew = "是";// 是否新品（是/否）
        String g_isrecom = request.getParameter("g_isrecom"); // 是否推荐（是/否）
        String g_remark = request.getParameter("g_remark"); // 商品备注

        Date create_time = new Date(); // 时间

        String org_id = "厦门";//
        String g_create_u_num = "01"; //

        Goods g = new Goods();
        g.setId(id);
        g.setGnum(g_num);
        g.setTnum(t_num);
        g.setGname(g_name);
        g.setGprice(g_price);
        g.setGdiscount(g_discount);
        g.setGstate(g_state);
        g.setGisdiscount(g_isdiscount);
        g.setGimg(filename);
        g.setGisnew(g_isnew);
        g.setGisrecom(g_isrecom);
        g.setGremark(g_remark);
        g.setCreateTime(create_time); // 时间
        g.setOrgId(org_id);
        g.setuCreateUnum(g_create_u_num);
        if (goodsDao.insert(g)) {
            MessageUtil.toast("商品添加成功！", session);
        } else {
            MessageUtil.toast("商品添加失败！", session);
        }

        Images img = null;
        for(int i=1;i<files.getCount();i++){
            f= files.getFile(i);
            if(!CommonUtil.isNull(f.getFileExt())) {
                img = new Images();
                id = DbUtil.getUUID();
                img.setId(id);
                img.setIpic(g.getGnum());
                String name = id + "." + f.getFileExt();
                img.setImessage(name);
                if(images.insert(img)) {
                    f.saveAs(root + "\\" + name);
                }
            }
        }

        resp.sendRedirect("query");

    }
    /**
     *
     * @param req
     * @param resp
     * @param session
     * @param pageIndex
     * @param key
     * @param order
     * @throws ServletException
     * @throws IOException
     */
    @Rout(url = "query")
    public void query(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
                      @Param("pageindex") String pageIndex,
                      @Param("keyword") String key,
                      @Param("order") String order) throws ServletException, IOException {

        //获取历史分页
        DataPageImp<Goods> goodsPage = CommonUtil.convert(session.getAttribute("goodsPage"), DataPageImp.class);
        //获取历史查询模板
        Goods goods = CommonUtil.convert(session.getAttribute("goodEntity"), Goods.class);
        if (goodsPage == null) {
            goodsPage = new DataPageImp<Goods>(null, 0);
            goodsPage.setPageSize(20);
        }
        if (goods == null) {
            goods = new Goods();
        }
        //设置条件参数
        if (key != null) {
            goods.setId(key);
            goods.setTnum(key);
           // goods.setuCreateUnum(key);
            goods.setGisdiscount(key);
            goods.setGisnew(key);
            goods.setGnum(key);
            goods.setGisrecom(key);
            goods.setGname(key);
            session.setAttribute("goodEntity", goods);
            session.setAttribute("key", key);
        }
        //设置页面显示参数
        if (!CommonUtil.isNull(order)) {
            session.setAttribute("order", order);
            goodsDao.setOrder(order);
        }
        //判断页面索引是否为空

        if (!CommonUtil.isNull(pageIndex)) {
            goodsPage.setNowPage(CommonUtil.toInt(pageIndex));
        }
        //设置查询连接条件 and or
        goodsDao.setJoin("or");
        //分页查询
        IPage<Goods> page = goodsDao.selectPage(goodsPage, goods);
        //保存时间
        SpoceAttributeUtil.setAttribute("goodsPage", page, session);
        //页面转发
        req.getRequestDispatcher("../admin/goods/goodsManager.jsp").forward(req, resp);
      //  SpoceAttributeUtil.removeAttribute("goodsPage", session);
    }
    /**
     * 设置查询状态
     *
     * @param req
     * @param session
     * @param state
     */
    @Rout(url = "queryState")
    public void setQuery(HttpServletRequest req, HttpSession session,
                         HttpServletResponse resp, @Param("state") String state) throws ServletException, IOException {
        Goods goods = new Goods();
        if (state != null) {
            goods.setGstate(state);
            session.removeAttribute("key");
        }
        session.setAttribute("goodEntity", goods);
        resp.sendRedirect("query");
    }
    /**
     * 修改页面
     *
     * @param req
     * @param session
     * @param resp
     * @param gnum
     */
    @Rout(url = "update", method = "get")
    public void updateJsp(HttpServletRequest req, HttpSession session, HttpServletResponse resp, @Param("gnum") String gnum) throws IOException, ServletException {
        if (CommonUtil.isNull(gnum)) {
            MessageUtil.toast("请选择需要修改的商品！", session);
            resp.sendRedirect("query");
            return;
        }
        //设置所有类型
        req.setAttribute("types", typeDao.select(null));
        Goods g = new Goods();
        g.setGnum(gnum);
        g = CommonUtil.getFirst(goodsDao.select(g));
        if (CommonUtil.isNull(g)) {
            MessageUtil.toast("请选择需要修改的商品！", session);
            resp.sendRedirect("query");
            return;
        }

        req.setAttribute("goods", g);

        req.getRequestDispatcher("../admin/goods/updateGoods.jsp").forward(req, resp);

    }

    @Rout(url = "update", method = "post")
    public void update(HttpServletRequest req, HttpServletResponse resp, HttpSession session)
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

        String id = request.getParameter("g_id");

        String fileName = id + "." + exm;
        String g_num = request.getParameter("g_num"); // 编号
        String t_num = request.getParameter("t_num"); // 类型编号
        String g_name = request.getParameter("g_name"); // 商品名称
        String g_Price = request.getParameter("g_price"); // 商品价格
        double g_price = CommonUtil.toDouble(g_Price);
        String g_Discount = request.getParameter("g_discount"); // 商品折扣
        float g_discount = CommonUtil.toFloat(g_Discount);
        String g_state = request.getParameter("g_state"); // 商品状态
        String g_isdiscount = request.getParameter("g_isdiscount"); // 是否折扣（是/否）

        String g_isnew = request.getParameter("g_isnew"); // 是否新品（是/否）
        String g_isrecom = request.getParameter("g_isrecom"); // 是否推荐（是/否）
        String g_remark = request.getParameter("g_remark"); // 商品备注

        String org_id = "厦门"; //

        User u = TypeUtil.convert(session.getAttribute("LOGIN_USER"), User.class);
        String g_reate_u_num = "01"; //
        Goods g = goodsDao.selectSimpleEntityByNum(id);
        g.setGnum(g_num);
        g.setTnum(t_num);
        g.setGname(g_name);
        g.setGprice(g_price);
        g.setGdiscount(g_discount);
        g.setGstate(g_state);
        g.setGisdiscount(g_isdiscount);
        g.setGisnew(g_isnew);
        g.setGisrecom(g_isrecom);
        g.setGremark(g_remark);

        g.setOrgId(org_id);
        g.setuCreateUnum(g_reate_u_num);

        if (!"".equals(exm))
            g.setGimg(fileName);

        if (goodsDao.update(g)) {
            if (!"".equals(exm)) {
                String path = getServletContext().getRealPath("/") + "images/goodspic/" + g.getGimg();
                java.io.File fs = new java.io.File(path);
                if (fs.exists()) {
                    fs.delete();
                }
                try {

                    f.saveAs(path, SmartUpload.SAVE_PHYSICAL); // 2进制的方式存储图片
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            MessageUtil.toast("修改商品信息成功！", session);
        } else {
            MessageUtil.toast("修改商品信息失败！", session);
        }
        resp.sendRedirect("query");

    }
}

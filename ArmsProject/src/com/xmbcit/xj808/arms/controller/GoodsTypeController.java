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
import com.xmbcit.xj808.arms.services.GoodsTypeimp;
import com.xmbcit.xj808.arms.entity.GoodsType;
import com.xmbcit.xj808.arms.base.routing.Rout;
import com.xmbcit.xj808.arms.base.routing.RoutController;
import com.xmbcit.xj808.arms.base.routing.auto.Param;
import com.xmbcit.xj808.arms.util.*;

@WebServlet(name = "GoodsTypeController", urlPatterns = {"/goodstype/*"})
public class GoodsTypeController extends RoutController {

    private static final long serialVersionUID = 1L;
    private MyDBConn conn = new MyDBConn();
    private GoodsTypeimp typeDao = new GoodsTypeimp(conn);

    @Override
    protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
            throws ServletException, IOException {
        req.getRequestDispatcher("query").forward(req, resp);

    }

    @Rout(url = "add", method = "GET")
    public void addUi(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("../admin/type/addType.jsp");
    }


    @Rout(url = "add", method = "POST")
    public void add(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException, ServletException {
        String id = DbUtil.getUUID(); // id
        String t_num = TableCountUtil.getGoodsTypeNum();
        String t_code = t_num;
        String t_name = req.getParameter("n_t_name");
        String t_remark = req.getParameter("n_tremark");
        Date create_times = new Date(); // 时间
        String org_id = "厦门";
        String t_create_u_num = "01";

        GoodsType t = new GoodsType();

        t.setTname(t_name);
        List<GoodsType> tys = typeDao.select(t);
        t.setId(id);
        t.setTnum(t_num);
        t.setTcode(t_code);
        t.setTremark(t_remark);
        t.setCreateTime(create_times);
        t.setOrgId(org_id);
        t.setuCreateUnum(t_create_u_num);
        if (typeDao.insert(t)) {
            MessageUtil.toast("类型添加成功！", session);
//            resp.sendRedirect("../admin/type/addType.jsp");
        } else {
            MessageUtil.toast("类型添加失败！", session);
            req.getRequestDispatcher("../admin/type/addType.jsp").forward(req, resp);
        }
        resp.sendRedirect("query");
    }


    @Rout(url = "query")
    public void query(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
                      @Param("pageindex") String pageIndex,
                      @Param("keyword") String key,
                      @Param("order") String order) {
        try {

            //获取历史分页
            DataPageImp<GoodsType> typePage = CommonUtil.convert(session.getAttribute("typePage"), DataPageImp.class);
            //获取历史查询模板
            GoodsType type = CommonUtil.convert(session.getAttribute("typeEntity"), GoodsType.class);
            if (typePage == null) {
                typePage = new DataPageImp<GoodsType>(null, 0);
                typePage.setPageSize(20);
            }
            if (type == null) {
                type = new GoodsType();
            }
            //设置条件参数
            if (key != null) {
                type.setId(key);
                type.setTnum(key);
                type.setTname(key);
                type.setTcode(key);
                type.setTremark(key);
                session.setAttribute("typeEntity", type);
                session.setAttribute("key", key);
            }
            //设置页面显示参数
            if (!CommonUtil.isNull(order)) {
                session.setAttribute("order", order);
                typeDao.setOrder(order);
            }
            //判断页面索引是否为空
            if (!CommonUtil.isNull(pageIndex)) {
                typePage.setNowPage(CommonUtil.toInt(pageIndex));
            }
            //设置查询连接条件 and or
            typeDao.setJoin("or");
            //分页查询
            IPage<GoodsType> page = typeDao.selectPage(typePage, type);
            //保存时间
            SpoceAttributeUtil.setAttribute("typePage", page, session);
            //页面转发
            req.getRequestDispatcher("../admin/type/typeManager.jsp").forward(req, resp);
          //  SpoceAttributeUtil.removeAttribute("typePage",session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//select * from goods_type  where 1<>1  or id='01' or t_code='01' or t_name='%01%' or t_num='%01%' or t_remark='%01%'order by create_time limit 0,2


    @Rout(url = "update", method = "post")
    public void logout(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        GoodsType updateType = TypeUtil.convert(session.getAttribute("goods"),GoodsType.class);
        session.removeAttribute("goods");
        if(CommonUtil.isNull(updateType)){
            MessageUtil.toast("类型不存在！",session);
            resp.sendRedirect("../admin/type/typeManager.jsp");
            return;
        }
        String t_name = req.getParameter("t_name");
        String t_remark = req.getParameter("t_remark");
        updateType.setTname(t_name);
        updateType.setTremark(t_remark);
        if(typeDao.update(updateType)){
            MessageUtil.toast("修改成功！",session);
        }else{
            MessageUtil.toast("修改失败！",session);
        }
        resp.sendRedirect("query");
    }


    @Rout(url = "update", method = "GET")
    public void update(HttpServletRequest req, HttpSession session, HttpServletResponse resp) throws IOException, ServletException {
        String typenum = req.getParameter("typenum");
        typeDao.setJoin("or");
        GoodsType goodsType = new GoodsType();
        goodsType.setTnum(typenum);
        GoodsType gt = CommonUtil.getFirst(typeDao.select(goodsType));
        if (gt == null) {
            MessageUtil.toast("选择修改类型不存在！", session);
            resp.sendRedirect("../admin/type/typeManager.jsp");
        } else {
            session.setAttribute("goods", gt);
            resp.sendRedirect("../admin/type/updateType.jsp");
        }
    }

    @Rout(url = "queryName")
    public void isExixName(HttpServletRequest request, PrintWriter out) {
        String typeName = request.getParameter("typename");
        GoodsType gt = new GoodsType();
        gt.setTnum(typeName);
        typeDao.setJoin("or");
        List<GoodsType> types = typeDao.select(gt);
        for (GoodsType g : types) {
            if (g.getTname().equals(gt.getTname())) {
                out.println("{\"msg\":\"exists\"}");
                return;
            }
        }
        out.println("{\"msg\":\"\"}");
    }

}

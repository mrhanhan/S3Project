package com.xmbcit.xj808.arms.controller;

import com.xmbcit.xj808.arms.entity.*;
import com.xmbcit.xj808.arms.base.routing.Rout;
import com.xmbcit.xj808.arms.base.routing.RoutController;
import com.xmbcit.xj808.arms.base.routing.auto.Param;
import com.xmbcit.xj808.arms.services.*;
import com.xmbcit.xj808.arms.vo.CartFactory;
import com.xmbcit.xj808.arms.vo.OrderGoodsDateils;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.MessageUtil;
import com.xmbcit.xj808.arms.util.MyDBConn;
import com.xmbcit.xj808.arms.util.TypeUtil;
import com.xmbcit.xj808.arms.util.json.Json;
import com.xmbcit.xj808.arms.util.json.JsonUtil;
import com.xmbcit.xj808.arms.vo.CsGsDs;
import com.xmbcit.xj808.arms.vo.DataScoure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ShopGoodsController", urlPatterns = {"/shopgoods/*"})
public class ShopGoodsController extends RoutController {

    private CartFactory factory = CartFactory.getFactory();

    private MyDBConn myDBConn = new MyDBConn();

    private GoodsImp goodsImp = new GoodsImp(myDBConn);
    private GoodsTypeimp typeimp = new GoodsTypeimp(myDBConn);
    private ImagesImpl images = new ImagesImpl(myDBConn);
    private DataScoure ds = DataScoure.getScoure();
    private OrderInfoImpl infoDao = new OrderInfoImpl(myDBConn);
    private OrderDatialDaoImp oddDao = new OrderDatialDaoImp(myDBConn);
    @Override
    protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        resp.sendRedirect("../shop");
    }

    /**
     * 热门商品
     *
     * @param req
     * @param resp
     * @param out
     */
    @Rout(url = "hotgoods")
    public void getHotGoods(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) {
        resp.setContentType("application/json");
        resp.setBufferSize(1024*1024);
        Json root = new Json("Hot");
        /*"{ 'Hot':[
               {
               "type":{},
                "goods":{}
               },

         ] }";
        */
        //热门商品
        Json[] json = null;
        Map<String, Goods> goodsMap = ds.getGoodsMap();//商品
        Map<String, GoodsType> typeMap = ds.getTypeGoods();//商品
        List<Json> goods = new ArrayList<>();
        Json j = null;
        int i = 0;
        int size = 15;
        for (Goods g : goodsMap.values()) {
            if (i >= size)
                break;
            //       if ("是".equals(g.getGisdiscount()) || "是".equals(g.getGisnew()) || "是".equals(g.getGisrecom())) {
            j = new Json("Good");
            j.setChildValue(JsonUtil.convertJson(typeMap.get(g.getTnum()), "type"));
            j.setChildValue(JsonUtil.convertJson(g, "goods"));
            i++;
            goods.add(j);
            //}
        }
        json = new Json[goods.size()];
        goods.toArray(json);
        root.setObjs(json);
        out.println(JsonUtil.JsonToString(root));
    }

    @Rout(url = "queryGoods")
    public void queryGoods(HttpServletRequest res, HttpServletResponse resp, HttpSession session,
                           @Param("gnum") String gnum/*查看的商品信息*/) throws ServletException, IOException {
        if(!CommonUtil.isNull(gnum)) {
            Goods g = ds.getGoodsMap().get(gnum);
            if(!CommonUtil.isNull(g)){
                res.setAttribute("good",g);
                res.setAttribute("type",ds.getTypeGoods().get(g.getTnum()));
                Images img = new Images();
                img.setIpic(g.getGnum());
                res.setAttribute("goodsimgs",images.select(img));
                res.getRequestDispatcher("../shopping/home/goodsinfo.jsp").forward(res, resp);
                return;
            }
        }
       resp.sendRedirect("../shop/query");
    }

    /**
     * 客户的商品订单
     * @param req
     * @param resp
     * @param session
     */
    @Rout(url ="orderQuery")
    public void orderQuery(HttpServletRequest req,HttpServletResponse resp,HttpSession session) throws IOException, ServletException {
        Customer login = TypeUtil.convert(session.getAttribute("LOGIN_CUS"), Customer.class);
        if (login == null) {
            MessageUtil.toast("请先进行登陆！", session);
            //转发
            resp.sendRedirect("../useroperation/login");
        } else {
            OrderInfo oi = new OrderInfo();
            oi.setCnum(login.getCnum());
            Map<String,Goods> goodsMap = ds.getGoodsMap();
            List<CsGsDs> csGsDsList = new ArrayList<>();//订单详细列表
            CsGsDs csGsDs = null;
            infoDao.setOrder("o_date desc");
            OrderDetails ode = new OrderDetails();
            OrderGoodsDateils ogd = null;
            for(OrderInfo io : infoDao.select(oi)){
                csGsDs = new CsGsDs();
                csGsDs.setCustomer(login);
                csGsDs.setOrder(io);
                ode.setOnum(io.getOnum());
                for(OrderDetails od : oddDao.select(ode)){
                    ogd = new OrderGoodsDateils();
                    ogd.setDetail(od);
                    ogd.setGoods(goodsMap.get(od.getGnum()));
                    csGsDs.getOrderDetails().add(ogd);
                }

                csGsDsList.add(csGsDs);
            }
            if(csGsDsList.size()>0)
            req.setAttribute("cusOrderList",csGsDsList);
            req.setAttribute("SIZE",csGsDsList.size()+1);
            req.getRequestDispatcher("../shopping/home/orderinfo.jsp").forward(req, resp);
        }
    }

}

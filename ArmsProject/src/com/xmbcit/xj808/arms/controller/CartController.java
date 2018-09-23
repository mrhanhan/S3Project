package com.xmbcit.xj808.arms.controller;

import com.xmbcit.xj808.arms.base.IConnect;
import com.xmbcit.xj808.arms.services.GoodsImp;
import com.xmbcit.xj808.arms.services.OrderDatialDaoImp;
import com.xmbcit.xj808.arms.services.OrderInfoImpl;
import com.xmbcit.xj808.arms.entity.*;
import com.xmbcit.xj808.arms.base.routing.Rout;
import com.xmbcit.xj808.arms.base.routing.RoutController;
import com.xmbcit.xj808.arms.vo.Cart;
import com.xmbcit.xj808.arms.vo.CartFactory;
import com.xmbcit.xj808.arms.services.OrderGoodsDateils;
import com.xmbcit.xj808.arms.util.*;
import com.xmbcit.xj808.arms.util.json.Json;
import com.xmbcit.xj808.arms.util.json.JsonUtil;
import com.xmbcit.xj808.arms.vo.DataScoure;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

@WebServlet(name = "CartController", urlPatterns = "/cart/*")
public class CartController extends RoutController {
    private CartFactory factory = CartFactory.getFactory();

    private MyDBConn myDBConn = new MyDBConn();

    private GoodsImp goodsImp = new GoodsImp(myDBConn);


    private DataScoure ds = DataScoure.getScoure();


    @Override
    public void init() throws ServletException {
        super.init();

    }

    @Override
    protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        resp.sendRedirect("../shop");
    }

    /**
     * 购物车页面
     *
     * @param req
     * @param resp
     */
    @Rout(url = "cart")
    public void cart(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException, ServletException {
        Customer login = TypeUtil.convert(session.getAttribute("LOGIN_CUS"), Customer.class);
        if (login == null) {
            MessageUtil.toast("请先进行登陆！", session);
            //转发
            resp.sendRedirect("../useroperation/login");
        } else {
            req.getRequestDispatcher("shopping/home/cart.jsp").forward(req, resp);
        }
    }
    /**
     * 获取购物车单个信息
     *
     * @param req
     * @param resp
     * @param out
     * @param session
     */
    @Rout(url = "get")
    public void getChat(HttpServletRequest req, HttpServletResponse resp, PrintWriter out, HttpSession session) throws IOException {
        resp.setContentType("application/json");
        Customer login = TypeUtil.convert(session.getAttribute("LOGIN_CUS"), Customer.class);
        Cart c = null;//购物车
        Json root = new Json("cart");
        Json jsLogin = new Json("login");

        Map<String, GoodsType> typeMap = ds.getTypeGoods();

        if (CommonUtil.isNull(login)) {
            jsLogin.setValue(false);
            root.setChildValue(new Json("msg", "请先登录"));
            root.setChildValue(jsLogin);
        } else {
            root.setChildValue(JsonUtil.convertJson(login, "login"));
            c = factory.getCart(login);
            List<OrderGoodsDateils> ogds = c.getBuyList();//获取购买商品的集合

            Json buys = new Json("buys");
            List<Json> js = new ArrayList<>();//json对象集合
            Json buy = null;
            int size = 0;//总数量
            int count = 0;
            double price = 0;
            BigDecimal summoney = new BigDecimal(0);//现价
            BigDecimal summoney1 = new BigDecimal(0);//原价
            BigDecimal a = new BigDecimal(0), b = new BigDecimal(0);
            for (OrderGoodsDateils ogd : ogds) {
                buy = new Json("buy");
                Goods g = ogd.getGoods();
                count = ogd.getOrderDetails().getDcount();//购买数量
                buy.setChildValue(JsonUtil.convertJson(g, "good"),//商品信息
                        JsonUtil.convertJson(typeMap.get(g.getTnum()), "type"),//类型信息
                        new Json("count", count));//数量信息
                boolean isDis = "否".equals(ogd.getGoods().getGisdiscount());//判断是否是折扣单价信息
                size += count;
                price = isDis ? g.getGprice() : g.getGdiscount() * g.getGprice() / 100;
                a = new BigDecimal(count);
                b = new BigDecimal(price);
                BigDecimal bd = a.multiply(b, MathContext.DECIMAL128);
                summoney = summoney.add(bd);
                b = new BigDecimal(g.getGprice());
                summoney1 = summoney1.add(a.multiply(b));
//                String str = bd.toString();
//                int dot = str.indexOf('.');
//                if (dot != -1 && str.length() - dot > 4) {
//                    str = str.substring(0, dot + 4);
//                }
                buy.setChildValue(new Json("price", bd));
                js.add(buy);
            }
            buys.setObjs(js.toArray());
            root.setChildValue(buys, new Json("count", size));
            String str = summoney.toString();
//            int dot = str.indexOf('.');
//            if (dot != -1 && str.length() - dot > 4) {
//                str = str.substring(0, dot + 4);
//            }
            root.setChildValue(buys, new Json("money", summoney));
//            str = summoney1.toString();
//            dot = str.indexOf('.');
//            if (dot != -1 && str.length() - dot > 4) {
//                str = str.substring(0, dot + 4);
//            }
            root.setChildValue(buys, new Json("money1", summoney1));//原价
        }
        out.println(JsonUtil.JsonToString(root));
    }

    @Rout(url = "add")
    public void setGoods(HttpServletRequest req, HttpServletResponse resp, PrintWriter out, HttpSession session) {
        try {
            resp.setContentType("application/json");
            Customer login = TypeUtil.convert(session.getAttribute("LOGIN_CUS"), Customer.class);
            Map<String, Goods> goodsMap = ds.getGoodsMap();

            String gnum = req.getParameter("goodsNum");
            int buynum = CommonUtil.toInt(req.getParameter("buyNum"));

            Json json = new Json("cart");
            if (!CommonUtil.isNull(login)) {
                json.setChildValue(JsonUtil.convertJson(login, "login"));
                Cart cart = factory.getCart(login);//获取购物车
                OrderGoodsDateils details = cart.getGoodsDetails(gnum);//获取订单

                boolean isdis = false;//是否大泽

                if (details == null) {
                        details = new OrderGoodsDateils();
                }
                Goods g = goodsMap.get(gnum);//获取商品信息
                OrderDetails od = details.getOrderDetails();

                if (od == null) {
                    od = new OrderDetails();
                    od.setGnum(g.getGnum());
                } else {

                }
                System.out.println(od.getDcount());
                od.setDcount(od.getDcount() + buynum);//累加
                isdis = "是".equals(g.getGisdiscount());
                int buyCount = od.getDcount();//获取最终购买数量
                BigDecimal bd = new BigDecimal(isdis ? (buyCount * g.getGprice() * g.getGdiscount() / 100) : (buyCount * g.getGprice()));
                od.setDmoney(bd);
                details.setGoods(g);
                details.setOrderDetails(od);
                cart.setBulist(details);

                json.setChildValue(new Json("msg", buyCount < 1 ? "取消购买此类商品！" : "添加商品成功"));
                json.setChildValue(new Json("state", !(buyCount < 1)));
            } else {
                json.setChildValue(new Json("msg", "请先登录"));
                json.setChildValue(new Json("login", false));
            }
            out.println(JsonUtil.JsonToString(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交订单
     *
     * @param req
     * @param resp
     * @param out
     * @param session
     */
    @Rout(url = "submit", method = "POST")
    public void submit(HttpServletRequest req, HttpServletResponse resp, PrintWriter out, HttpSession session) {
        try {
            resp.setContentType("application/json");
            Customer login = TypeUtil.convert(session.getAttribute("LOGIN_CUS"), Customer.class);
            Map<String, Goods> goodsMap = ds.getGoodsMap();

            String gnum = req.getParameter("goodsNum");
            int buynum = CommonUtil.toInt(req.getParameter("buyNum"));

            Json json = new Json("cart");
            if (!CommonUtil.isNull(login)) {
                json.setChildValue(JsonUtil.convertJson(login, "login"));
                Cart cart = factory.getCart(login);//获取购物车
                OrderInfo oi = new OrderInfo();
                String onum = TableCountUtil.getOrderInfoNum();
                oi.setCnum(login.getCnum());
                oi.setOnum(onum);
                oi.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                oi.setOdate(new Date());
                oi.setCreateTime(new Date());
                oi.setOrgId("代发货");
                int size = 0;
                BigDecimal sumMoney = new BigDecimal(0);
                List<OrderDetails> orderDetails = new ArrayList<>();

                IConnect conn = myDBConn.newConn(true);//克隆连接
                conn.beginTrasn();//开启事务

                OrderDatialDaoImp odDao = new OrderDatialDaoImp(conn);

                OrderInfoImpl orderDao = new OrderInfoImpl(conn);
                OrderDetails od = null;
                boolean ok = false;
                if (cart.getBuyList().size() > 0) {
                    for (OrderGoodsDateils ogd : cart.getBuyList()) {
                        od = ogd.getOrderDetails();
                        od.setOnum(onum);
                        od.setOrgId("代发货");
                        od.setCreateTime(new Date());
                        orderDetails.add(od);
                        sumMoney = sumMoney.add(od.getDmoney());
                        size += od.getDcount();
                    }

                    oi.setOcount(size);
                    oi.setOmoney(sumMoney);

                    if (orderDao.insert(oi)) {
                        for (OrderDetails od1 : orderDetails) {
                            if (!odDao.insert(od1))
                                break;
                        }
                        ok = true;
                    }
                    if (ok) {
                        cart.getBuyMap().clear();
                        cart.getBuyList().clear();

                        conn.commitTrans();
                    }else{
                        conn.rollbackTrans();
                    }
                }
                conn.close();//关闭连接
                json.setChildValue(new Json("msg", ok ? "结算成功！" : "结算失败！"), new Json("state", ok));

            } else {
                json.setChildValue(new Json("msg", "请先登录"));
                json.setChildValue(new Json("login", false));
            }
            out.println(JsonUtil.JsonToString(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Rout(url = "clear")
    public void clear(HttpServletRequest req, HttpServletResponse resp, PrintWriter out, HttpSession session) {
        try {
            resp.setContentType("application/json");
            Customer login = TypeUtil.convert(session.getAttribute("LOGIN_CUS"), Customer.class);
            Map<String, Goods> goodsMap = ds.getGoodsMap();

            String gnum = req.getParameter("goodsNum");
            int buynum = CommonUtil.toInt(req.getParameter("buyNum"));

            Json json = new Json("cart");
            if (!CommonUtil.isNull(login)) {
                json.setChildValue(JsonUtil.convertJson(login, "login"));
                Cart cart = factory.getCart(login);//获取购物车
                cart.getBuyList().clear();//清除
                cart.getBuyMap().clear();//清除
            } else {
                json.setChildValue(new Json("msg", "请先登录"));
                json.setChildValue(new Json("login", false));
            }
            out.println(JsonUtil.JsonToString(json));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void destroy() {
        super.destroy();
        ds.stopFlush();
        myDBConn.close();
    }
}

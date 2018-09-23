package com.xmbcit.xj808.arms.controller;

import com.xmbcit.xj808.arms.services.Customerimp;
import com.xmbcit.xj808.arms.services.GoodsImp;
import com.xmbcit.xj808.arms.services.GoodsTypeimp;
import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.GoodsType;
import com.xmbcit.xj808.arms.base.routing.Rout;
import com.xmbcit.xj808.arms.base.routing.RoutController;
import com.xmbcit.xj808.arms.util.*;
import com.xmbcit.xj808.arms.vo.DataScoure;
import com.xmbcit.xj808.arms.vo.OrderGoodsGroup;
import com.xmbcit.xj808.arms.vo.Good;
import com.xmbcit.xj808.arms.vo.TypeGoods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 购物商品的controller
 */
@WebServlet(name = "ShopController", urlPatterns = {"/shop","/shop/queryGoods", "/shop/query"})
public class ShopController extends RoutController {
    private MyDBConn conn = new MyDBConn();
    private DataScoure ds = DataScoure.getScoure();
    private GoodsImp goodsDao = new GoodsImp(conn);
    private Customerimp cusDao = new Customerimp(conn);
    private GoodsTypeimp typesDao = new GoodsTypeimp(conn);


    @Override
    protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        resp.sendRedirect("../shop");
    }

    @Rout(url = "shop")
    public void home(HttpServletResponse resp, HttpServletRequest req, HttpSession session) throws IOException, ServletException {
        req.setAttribute("types", typesDao.select(null));
        GoodsType ty = new GoodsType();
        ty.setTremark("现时促销类商品！");
        ty.setTname("折扣商品");
        DataPageImp<Goods> gp = new DataPageImp<>(null, 0);
        gp.setPageSize(6);
        OrderGoodsGroup gg = new OrderGoodsGroup(ty, goodsDao.selectPage(gp, null).getNowPageData());
        /*保存需要显示的商品*/
        session.setAttribute("goods", new OrderGoodsGroup[]{gg, gg});
        req.getRequestDispatcher("shopping/home/index.jsp").forward(req, resp);
    }

    @Rout(url="queryGoods")
    public void queryGoode(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../shopgoods/queryGoods").forward(req,resp);
    }

    @Rout(url = "query")
    public void query(HttpServletResponse resp, HttpServletRequest req, HttpSession session) throws ServletException, IOException {
        String typeName = req.getParameter("typeName");
        String index = req.getParameter("pageIndex");

        String showName = req.getParameter("ShowName");

        if (typeName != null) {
            session.setAttribute("ShopTypeName", typeName);

        }
        if (showName != null) {
            session.setAttribute("ShopShowName", showName);
        }

        if (typeName == null) {
            Object o = session.getAttribute("ShopTypeName");
            typeName = o != null ? o.toString() : "";
            o = session.getAttribute("ShopShowName");
            showName = o != null ? o.toString() : "";

        }
        if (CommonUtil.isNull(showName))//展示名称
            showName = typeName;
        String typeNames[] = typeName.split("[\\||,|-| ]");
        System.out.println(Arrays.toString(typeNames));

        Map<String, TypeGoods> tgs = new HashMap<>();
        GoodsType temp = new GoodsType();
        Map<String, GoodsType> typeMap = ds.getTypeGoods();


        Goods g = new Goods();
        TypeGoods tg = null;//保持信息到前端
        typesDao.setJoin("or");
        int size = 0;

        List<GoodsType> ts = new ArrayList();
        ///关键字查询商品
        List<Goods> gs = null;
        for (String s : typeNames) {
            temp.setTname(s);
            ts.addAll(typesDao.select(temp));
        }
        g.setGnum(null);
        List<Good> data = new Vector<>();
        for (GoodsType gt : ts) {
            g.setTnum(gt.getTnum());
            gs = goodsDao.select(g);
            if (!gs.isEmpty()) {
                tg = new TypeGoods();
                tg.setType(gt);
                tg.getGoods().addAll(gs);

                tgs.put(gt.getTnum(), tg);
            }
        }
        g.setGnum(null);

        //查询关键字商品
        for (String s : typeNames) {
            g.setGname(s);
            //查询
            gs = goodsDao.select(g);

            for (Goods good : gs) {

                tg = tgs.get(good.getTnum());//获取类型所对应的封装类

                if (tg == null) {
                    tg = new TypeGoods();
                    tg.setType(typeMap.get(good.getTnum()));
                    tg.setGoods(new HashSet<>());
                    tgs.put(tg.getType().getTnum(), tg);
                }

                tg.getGoods().add(good);
            }
        }
        //计算商品个数
        size = 0;
        for (TypeGoods typeGoods : tgs.values()) {

            size += typeGoods.getGoods().size();
            for (Goods g1 : typeGoods.getGoods()) {
                if("下架".equals(g1.getGstate())){
                    size--;
                //    typeGoods.getGoods().remove(g1);
                }else
                data.add(new Good(g1,typeGoods.getType()));
            }
        }
        if (tgs.values().size() > 0)
            req.setAttribute("TGS", tgs.values().toArray());
        DataPageImp page = TypeUtil.convert(session.getAttribute("SHOPPAGE"), DataPageImp.class);

        if (page == null) {
            page = new LocalDataPage(null, 0);
            page.setNowPage(1);
            page.setPageSize(9);

        }

        if (!CommonUtil.isNull(index))
            page.setNowPage(CommonUtil.toInt(index));

        DataPageImp lpage = LocalDataPage.getPage(data, page);
        req.setAttribute("SIZE", size);
        if (CommonUtil.isNull(showName)) {
            showName = "军火库";
        }

//        req.setAttribute("TGS", tgs.values());
        session.setAttribute("SHOPPAGE", lpage);
        if (data.size() > 0) {
            SpoceAttributeUtil.setAttribute("shopPage", lpage, session);
        }else{
            SpoceAttributeUtil.removeAttribute("shopPage",session);
        }
        req.setAttribute("ShowName", showName);
        req.setAttribute("typeName", typeName);
        req.setAttribute("OtherTypes", typeMap.values());
        req.getRequestDispatcher("../shopping/home/shop.jsp").forward(req, resp);
    }
}

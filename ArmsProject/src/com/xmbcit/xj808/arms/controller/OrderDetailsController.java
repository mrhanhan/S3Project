package com.xmbcit.xj808.arms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xmbcit.xj808.arms.services.Customerimp;
import com.xmbcit.xj808.arms.services.GoodsImp;
import com.xmbcit.xj808.arms.services.OrderDatialDaoImp;
import com.xmbcit.xj808.arms.services.OrderInfoImpl;
import com.xmbcit.xj808.arms.entity.Customer;
import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.OrderDetails;
import com.xmbcit.xj808.arms.entity.OrderInfo;
import com.xmbcit.xj808.arms.base.routing.Rout;
import com.xmbcit.xj808.arms.base.routing.RoutController;
import com.xmbcit.xj808.arms.base.routing.auto.Param;
import com.xmbcit.xj808.arms.util.CommonUtil;
import com.xmbcit.xj808.arms.util.MessageUtil;
import com.xmbcit.xj808.arms.util.MyDBConn;
import com.xmbcit.xj808.arms.vo.CsGsDs;
import com.xmbcit.xj808.arms.vo.DataScoure;
import com.xmbcit.xj808.arms.vo.OrderGoodsDateils;

/**
 * 订单详情控制器
 * 
 * @author ALEX
 *
 */
@WebServlet(name = "OrderDetailsController", urlPatterns = "/orderdetails/*")
public class OrderDetailsController extends RoutController {
	private MyDBConn conn = new MyDBConn();
	private OrderDatialDaoImp odi = new OrderDatialDaoImp(conn);
	private OrderInfoImpl orderDao = new OrderInfoImpl(conn);
	private GoodsImp goodsDao = new GoodsImp(conn);
	private Customerimp cusDao = new Customerimp(conn);

	private DataScoure ds = DataScoure.getScoure();
	private static final long serialVersionUID = 1L;

	@Override
	protected void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out)
			throws ServletException, IOException {
				req.getRequestDispatcher("../admin/home.jsp").forward(req, resp);

	}

	@Rout(url = "queryorder")
	public void queryOrderInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
			@Param("onum") String onum) throws ServletException, IOException {
		OrderInfo orderinfo = new OrderInfo();
		orderinfo.setOnum(onum);
		orderinfo = CommonUtil.getFirst(orderDao.select(orderinfo));
		if (orderinfo == null) {
			MessageUtil.toast("订单有误！重新选择！", req);
			req.getRequestDispatcher("../order/query").forward(req, resp);
			return;
		}
		CsGsDs cgd = new CsGsDs();
		Customer customer = new Customer();
		customer.setCnum(orderinfo.getCnum());
		customer = CommonUtil.getFirst(cusDao.select(customer));
		cgd.setCustomer(customer);
		
		OrderDetails orderddetails = new OrderDetails();
		orderddetails.setOnum(orderinfo.getOnum());

		List<OrderGoodsDateils> goods = new ArrayList<>();
		Goods gt = new Goods(),gt1;
		OrderGoodsDateils goodsd = null;

		Map<String,Goods> goodsMap = ds.getGoodsMap();

		for (OrderDetails orderDetails : odi.select(orderddetails)) {
			goodsd = new OrderGoodsDateils();
			goodsd.setGoods(goodsMap.get(orderDetails.getGnum()));
			goodsd.setDetail(orderDetails);
			goods.add(goodsd);
		}
		cgd.setOrder(orderinfo);
		cgd.setOrderDetails(goods);
		
			session.setAttribute("cgd", cgd);
		req.getRequestDispatcher("../admin/orderdetails/orderDetailsManager.jsp").forward(req, resp);
	}

//	@Rout(url = "query")
//	public void queryOrderInfo(HttpServletRequest req, HttpServletResponse resp, HttpSession session,
//			@Param("pageindex") String pageIndex, @Param("keyword") String key, @Param("order") String order) {
//		try {
//			// 获取历史分页
//			DataPageImp<OrderDetails> orderDetailsPage = CommonUtil.convert(session.getAttribute("orderDetailsPage"),
//					DataPageImp.class);
//			// 获取历史查询模板
//			OrderDetails type = CommonUtil.convert(session.getAttribute("orderdetailsEntity"), OrderDetails.class);
//			if (orderDetailsPage == null) {
//				orderDetailsPage = new DataPageImp<OrderDetails>(null, 0);
//				orderDetailsPage.setPageSize(20);
//			}
//			if (type == null) {
//				type = new OrderDetails();
//			}
//			// 设置条件参数
//			if (key != null) {
//				type.setOnum(key);
//				type.setGnum(key);
//				session.setAttribute("orderdetailsEntity", type);
//				session.setAttribute("key", key);
//			}
//			// 设置页面显示参数
//			if (!CommonUtil.isNull(order)) {
//				session.setAttribute("order", order);
//				odi.setOrder(order);
//			}
//			// 判断页面索引是否为空
//			if (!CommonUtil.isNull(pageIndex)) {
//				orderDetailsPage.setNowPage(CommonUtil.toInt(pageIndex));
//			}
//			// 设置查询连接条件 and or
//			odi.setJoin("or");
//			// 分页查询
//
//			// 保存时间
//			SpoceAttributeUtil.setAttribute("orderDetailsPage", orderDetailsPage, session);
//			// 页面转发
//			req.getRequestDispatcher("../admin/orderdetails/orderDetailsManager.jsp").forward(req, resp);
//			// SpoceAttributeUtil.removeAttribute("typePage",session);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}

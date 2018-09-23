package com.xmbcit.xj808.arms.vo;

import java.util.ArrayList;
import java.util.List;

import com.xmbcit.xj808.arms.entity.Customer;
import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.OrderDetails;
import com.xmbcit.xj808.arms.entity.OrderInfo;

public class CsGsDs {
	private Customer customer ;
	
	private OrderInfo order;
	
	private List<OrderGoodsDateils> orderDetails = new ArrayList<OrderGoodsDateils>();
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
	public List<OrderGoodsDateils> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderGoodsDateils> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	
	

}
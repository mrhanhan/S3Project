package com.xmbcit.xj808.arms.services;

import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.OrderDetails;

/**
 * 订单商品明细
 */
public class OrderGoodsDateils {
    private Goods goods;//商品信息
    private OrderDetails orderDetails;//订单信息

    public OrderGoodsDateils() {
    }

    public OrderGoodsDateils(Goods goods, OrderDetails orderDetails) {
        this.goods = goods;
        this.orderDetails = orderDetails;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}

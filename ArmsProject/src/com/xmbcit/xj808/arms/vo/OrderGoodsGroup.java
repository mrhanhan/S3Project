package com.xmbcit.xj808.arms.vo;

import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.GoodsType;

import java.util.List;

/**
 * 订单容器
 */
public class OrderGoodsGroup {
    public GoodsType type;
    public List<Goods> goods;

    public OrderGoodsGroup() {
    }

    public OrderGoodsGroup(GoodsType type, List<Goods> goods) {
        this.type = type;
        this.goods = goods;
    }

    public GoodsType getType() {
        return type;
    }

    public void setType(GoodsType type) {
        this.type = type;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
}

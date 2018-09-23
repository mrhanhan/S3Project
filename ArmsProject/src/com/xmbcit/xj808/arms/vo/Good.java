package com.xmbcit.xj808.arms.vo;

import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.GoodsType;

public class Good {
    private Goods goods;
    private GoodsType type;


    public  Good(){

    }

    public Good(Goods goods, GoodsType goodsType) {
        this.goods = goods;
        this.type = goodsType;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GoodsType getType() {
        return type;
    }

    public void setType(GoodsType goodsType) {
        this.type = goodsType;
    }
}

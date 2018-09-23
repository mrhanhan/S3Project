package com.xmbcit.xj808.arms.vo;

import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.GoodsType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 商品类型集合
 */
public class TypeGoods {
    private GoodsType type;
    private Set<Goods> goods;

    public TypeGoods(){
        goods = new HashSet<>();
    }
    public GoodsType getType() {
        return type;
    }

    public void setType(GoodsType type) {
        this.type = type;
    }

    public Set<Goods> getGoods() {
        return goods;
    }

    public void setGoods(Set<Goods> goods) {
        this.goods = goods;
    }
}

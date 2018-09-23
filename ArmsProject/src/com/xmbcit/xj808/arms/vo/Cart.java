package com.xmbcit.xj808.arms.vo;

import com.xmbcit.xj808.arms.entity.Customer;
import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.OrderDetails;
import com.xmbcit.xj808.arms.services.OrderGoodsDateils;

import java.util.*;

/**
 * 购物车
 */
public class Cart {

    private Customer costomer;
    /*订单集合*/
    private Map<String, com.xmbcit.xj808.arms.services.OrderGoodsDateils> buyMap;
    /*购买商品的集合*/
    private List<com.xmbcit.xj808.arms.services.OrderGoodsDateils> buyList;


    public Cart(Customer costomer, Map<String, com.xmbcit.xj808.arms.services.OrderGoodsDateils> buyMap) {
        this.costomer = costomer;
        buyList = new ArrayList<>();
        buyMap = new HashMap<>();
    }


    public Cart() {
        buyList = new ArrayList<>();
        buyMap = new HashMap<>();
    }

    public Customer getCostomer() {
        return costomer;
    }

    public void setCostomer(Customer costomer) {
        this.costomer = costomer;
    }

    public List<com.xmbcit.xj808.arms.services.OrderGoodsDateils> getBuyList() {
        return buyList;
    }

    public Map<String, com.xmbcit.xj808.arms.services.OrderGoodsDateils> getBuyMap() {
        return buyMap;
    }

    public void setBuyList(List<com.xmbcit.xj808.arms.services.OrderGoodsDateils> buyList) {
        this.buyList = buyList;
    }

    /**
     * 根据商品编号，获取购买信息
     * @param num
     * @return
     */
    public com.xmbcit.xj808.arms.services.OrderGoodsDateils getGoodsDetails(String num){

        return buyMap.get(num);
    }
    /**
     * 设置订单详细
     *
     * @param og
     */
    public void setBulist(com.xmbcit.xj808.arms.services.OrderGoodsDateils og) {
        Goods g = og.getGoods();
        OrderDetails od = og.getOrderDetails();

             String gid = g.getGnum();//获取商品id

            OrderGoodsDateils ogd = buyMap.get(gid);
            if(ogd!=null) {
                buyList.remove(ogd);
                buyMap.remove(gid);
            }
            buyList.add(og);
            buyMap.put(gid,og);



        if (od.getDcount() <1) {//判断商品购买数量是否为空
            buyList.remove(og);
            buyMap.remove(gid);
        }
    }
}

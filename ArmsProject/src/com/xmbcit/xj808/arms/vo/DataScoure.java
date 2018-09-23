package com.xmbcit.xj808.arms.vo;

import com.xmbcit.xj808.arms.services.GoodsImp;
import com.xmbcit.xj808.arms.services.GoodsTypeimp;
import com.xmbcit.xj808.arms.services.OrderDatialDaoImp;
import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.entity.GoodsType;
import com.xmbcit.xj808.arms.util.MyDBConn;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源
 */
public class DataScoure extends Thread {


    private static DataScoure ds = null;
    public static  DataScoure getScoure(){
        if(ds == null){
            ds = new DataScoure();
            ds.start();
        }
        if(!ds.isFlush){
            ds.start();
        }
        return ds;
    }
    private volatile Map<String, Goods> goodsMap = new HashMap<>();
    private volatile Map<String, GoodsType> typeGoods = new HashMap<>();
    private MyDBConn myDBConn = new MyDBConn();
    private GoodsImp goodsImp = new GoodsImp(myDBConn);
    private GoodsTypeimp typeDao = new GoodsTypeimp(myDBConn);
    private OrderDatialDaoImp orderDatialDaoImp = new OrderDatialDaoImp(myDBConn);

    private Goods tempGoods;//查询模板
    private boolean isFlush = false;
    private DataScoure(){
        tempGoods = new Goods();
        tempGoods.setGstate("上架");
    }

    public Map<String, Goods> getGoodsMap() {
        return goodsMap;
    }

    public Map<String, GoodsType> getTypeGoods() {
        return typeGoods;
    }

    @Override
    public void run() {
        synchronized (this) {
            goodsMap.clear();
            while (isFlush) {
                goodsImp.setJoin("and");
                for (Goods g : goodsImp.select(tempGoods)) {
                    goodsMap.put(g.getGnum(), g);
                }
                for (GoodsType g : typeDao.select(null)) {
                    typeGoods.put(g.getTnum(), g);
                }
                try {
                    wait(5000);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    @Override
    public synchronized void start() {
        isFlush = true;
        super.start();
    }

    public synchronized void stopFlush(){

        isFlush = false;
        stop();
    }
}

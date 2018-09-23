package com.xmbcit.xj808.arms.test;

import com.xmbcit.xj808.arms.entity.Goods;
import com.xmbcit.xj808.arms.util.json.JsonUtil;

import java.text.DecimalFormat;
import java.util.Date;

public class TT {

    public static void main(String args[]){
        StringBuilder sb = new StringBuilder();
        Goods g = new Goods(new Date(),"0","0","0","0","0","0",100.0,100.0f,"0","0","0","0","0","0");
        System.out.println(JsonUtil.convertJson(g,"goods"));
    }
}

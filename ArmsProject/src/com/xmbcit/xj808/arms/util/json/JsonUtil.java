package com.xmbcit.xj808.arms.util.json;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class JsonUtil {

    private static Set<Class<?>> number = new HashSet<>();
    static {
        number.add(int.class);number.add(Integer.class);
        number.add(short.class);number.add(Short.class);
        number.add(byte.class);number.add(Byte.class);
        number.add(Float.class);number.add(float.class);
        number.add(Double.class);number.add(double.class);
        number.add(long.class);number.add(Long.class);
        number.add(boolean.class);number.add(Boolean.class);
    }

    private JsonUtil(){}

    /**
     * 吧json转换为json字符串s
     * @param root
     * @return
     */
    public static String JsonToString(Json root){
        StringBuilder sb = new StringBuilder("{");
        sb.append(paseJson(root));
        sb.append("}");
        return sb.toString();
    }
    /**
     * 转换单个json对象
     * @param item
     * @return
     */
    private static StringBuilder paseJson(Json item){
        StringBuilder sb = new StringBuilder("\"").append(item.getName()).append("\":");

        Object v = item.getValue();
        Object [] objs = item.getObjs();
        Set<Json> ch = item.getChildValue();
        if(v!=null){
            sb.append(getJsonTypeStr(v));
        }else if(objs!=null) {
            sb.append("[");
            for(int i = 0;i<objs.length;i++){

                    sb.append(getJsonTypeStr(objs[i]));

                if(i<objs.length-1){
                    sb.append(",\n");
                }
            }
            sb.append("]");
        }else{
            int s = ch.size();
            int i=0;
            sb.append("{");
            for(Json j  :ch){
                sb.append(paseJson(j).toString());
                if(i++<s-1){
                    sb.append(",\n");
                }
            }
            sb.append("}");
        }

        return sb;

    }

    private static  String getJsonTypeStr(Object o){
        Class c = o.getClass();

        if(c.equals(double.class)|| c.equals(BigDecimal.class) ||c.equals(Double.class)){

            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setGroupingUsed(false);

            return decimalFormat.format(o);
        }
        else if(number.contains(c)){
            return o+"";
        }else if(o.getClass().equals(Json.class))
            return o.toString();

        String s =  "\""+((o+""))+"\"";

        return  s;
    }


    public static  Json convertJson(Object o ,String name){
        Json root = new Json(name);
        Set<Json> child =new HashSet<>();
        Class<?> oc = o.getClass();
        Field [] fs =  oc.getFields();
        for(Field f : fs){
            f.setAccessible(true);
            try {
                child.add(new Json(f.getName(),f.get(o)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        fs =  oc.getDeclaredFields();
        for(Field f : fs){
            f.setAccessible(true);
            try {
                child.add(new Json(f.getName(),f.get(o)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        root.setChildValue(child);
        return root;
    }

}

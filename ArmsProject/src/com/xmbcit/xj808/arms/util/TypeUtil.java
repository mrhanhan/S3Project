package com.xmbcit.xj808.arms.util;

/**
 * 类型工具包
 */
public class TypeUtil {
    /**
     * 判断类型b，是否属于或者等于类型a
     * @param a
     * @param b
     * @return
     */
    public static boolean belong(Class<?> a,Class<?> b){
        if(a.equals(b)){
            return  true;
        }
        try{
            b.asSubclass(a);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    /**
     * 数据类型转换<br/>
     * A a = convert(obj,A.class);
     * @param o 需要转化你的类型
     * @param cls 类型.class
     * @param <E>
     * @return  转换成功返回E类型，失败返回null
     */
    public static <E> E convert(Object o ,Class<E> cls){
        if(o==null){
            return null;
        }
        Class<?> c = o.getClass();
        if(c == cls){

        }
        try {
            if (c.asSubclass(cls) != null) {
                return (E)o;
            }
        }catch (Exception e){

        }
        return null;
    }
}

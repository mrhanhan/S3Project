package com.xmbcit.xj808.arms.base.routing;

import com.jspsmart.upload.Request;
import com.xmbcit.xj808.arms.base.routing.auto.Param;
import com.xmbcit.xj808.arms.util.TypeUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * 路由方法类
 */
public class RoutMethod {
    /*路由的路径*/
    private String url;
    /**
     * 路由的方法
     */
    private Method method;
    /**
     * 请求类型
     */
    private String type;
    /**
     * 是否文件上传
     */
    private boolean isUpLoad ;

    public RoutMethod(Method method, Rout rout) {
        this.url = rout.url();
        this.method = method;
        this.type = rout.method();
        isUpLoad = rout.isUpload();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 当前请求类型，是否适用于制定请求类型
     *
     * @param m
     * @return
     */
    public boolean isRequestMethon(String m) {
        if (type.isEmpty())
            return true;
        else {

            return type.toLowerCase().equals(m.toLowerCase());
        }
    }

    public boolean isUpLoad() {
        return isUpLoad;
    }

    /**
     * 调用/执行路由方法
     *
     * @param controller
     *
     */
    public boolean call(RoutController controller,Object ...parmas) {
        method.setAccessible(true);
        Map<Class<?>,Object> parMap = new HashMap<>();
        for(Object o : parmas){
            if(o!=null)
            parMap.put(o.getClass(),o);
        }
        List<Object> paramsList = new ArrayList<>();
        //获取参数个数
        Parameter[] ps  = method.getParameters();
        Class<?> c1=null,c2=null;
        Param param = null;//参数注解
        /*获取两种请求参数*/
        HttpServletRequest request1 = getTypeValue(parMap,HttpServletRequest.class);
        Request request2 = getTypeValue(parMap,Request.class);
        ServletContext context =  request1.getServletContext();
        HttpSession session = getTypeValue(parMap,HttpSession.class);
        String name = null;
        Object o = null;
        try {
            for(Parameter p : ps){

                param = p.getAnnotation(Param.class);
                if(param !=null){
                    name = param.value();//获取请求的名称
                    name = name.isEmpty()?p.getName():name;//如果名字为空，则和参数名相同
                    if(isUpLoad){
                        o = request2.getParameter(name);
                    }else{
                        o = request1.getParameter(name);
                    }

                    if(o!=null && !TypeUtil.belong(p.getType(),o.getClass())){
                        o = null;
                    }

                }else{
                    o = getTypeValue(parMap,p.getType());
                }

                paramsList.add(o);
            }
            System.out.println(paramsList);

                method.invoke(controller,paramsList.toArray());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 在几何中，获取指定类型的参数
     * @param map
     * @param type
     * @return
     */
    private <E> E getTypeValue(Map<Class<?>,Object> map,Class<E> type){
        Object o =null;
        Set<Class<?>> keyset = map.keySet();
        for(Class<?> c : keyset){
            if(type.equals(c)) {//判断类型是否相同
                o = map.get(c);
               break;
            }
            try {
               c.asSubclass(type);//判断你是否是其子类型
                o = map.get(c);
                break;

            }catch (Exception e){}
        }
        if(o!=null)
            return (E)o;
        else
            return null;

    }


}

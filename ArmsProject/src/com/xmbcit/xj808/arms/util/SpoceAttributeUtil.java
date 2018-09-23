package com.xmbcit.xj808.arms.util;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作用于存放对象工具类<br/>
 * 在解析存放对象时候会提供对象名称和对象<br/>
 * 此工具主要解决，不是javabean，也可以在在EL表达式中访问
 * class a{
 * int a = 10;
 * }
 * <p>
 * save("a",new a());
 * request.setAttribute("a.a",a.a);
 * <p>
 * class a{
 * int a = 10;
 * int getA();
 * }
 * <p>
 * save("a",new a());
 * request.setAttribute("a.a",a.getA());
 */
public class SpoceAttributeUtil {
    private SpoceAttributeUtil() {

    }

    /**
     * 在Request存放内容值
     *
     * @param objName
     * @param value
     * @param request
     */
    public static void setAttribute(String objName, Object value, HttpServletRequest request) {
        Class<?> cls = value.getClass();
        Map<String, Object> datas = parseData(cls, value);
        for (String fNames : datas.keySet()) {
            request.setAttribute(objName + "." + fNames, datas.get(fNames));
        }
    }

    /**
     * 在Session存放内容值
     *
     * @param objName
     * @param value
     * @param request
     */
    public static void setAttribute(String objName, Object value, HttpSession request) {
        Class<?> cls = value.getClass();
        request.setAttribute(objName,value);
        Map<String, Object> datas = parseData(cls, value);
        for (String fNames : datas.keySet()) {
            request.setAttribute(objName + "$" + fNames, datas.get(fNames));
        }
    }

    /**
     * 在Request存放内容值
     *
     * @param objName
     * @param value
     * @param request
     */
    public static void setAttribute(String objName, Object value, ServletContext request) {
        Class<?> cls = value.getClass();
        request.setAttribute("objName",value);
        Map<String, Object> datas = parseData(cls, value);
        for (String fNames : datas.keySet()) {
            request.setAttribute(objName + "$" + fNames, datas.get(fNames));
        }
    }

    /**
     * 移除对象
     *
     * @param objName
     * @param request
     */
    public static void removeAttribute(String objName, HttpServletRequest request) {
        request.removeAttribute(objName);
        Enumeration<String> namse = request.getAttributeNames();
        String name = "";
        for (; namse.hasMoreElements(); ) {
            name = namse.nextElement();
            if (name.indexOf(objName) == 0) {
                request.removeAttribute(name);
            }
        }
    }

    /**
     * 移除对象
     *
     * @param objName
     * @param request
     */
    public static void removeAttribute(String objName, HttpSession request) {
        Enumeration<String> namse = request.getAttributeNames();
        request.removeAttribute(objName);
        String name = "";
        for (; namse.hasMoreElements(); ) {
            name = namse.nextElement();
            if (name.indexOf(objName) == 0) {
                request.removeAttribute(name);
            }
        }
    }

    /**
     * 解析内容数据
     *
     * @param cls
     * @param value
     * @return
     */
    private static Map<String, Object> parseData(Class<?> cls, Object value) {
        Map<String, Object> obj = new HashMap<>();
        Field[] fields = cls.getFields();
        Method[] ms = cls.getMethods();
        String name = null;
        Object o = null;

        try {
            for (Field f : fields) {
                f.setAccessible(true);
                name = ("get" + f.getName()).toLowerCase();
                o = f.get(value);

                for (Method m : ms) {
                    m.setAccessible(true);
                    if (m.getName().toLowerCase().equals(name)) {
                        if (m.getReturnType() != Void.class) {
                            o = m.invoke(value);
                        }
                    }
                }
                obj.put(f.getName(), o);

            }
           fields = cls.getDeclaredFields();
             ms = cls.getDeclaredMethods();
            for (Field f : fields) {
                f.setAccessible(true);
                name = ("get" + f.getName()).toLowerCase();
                o = f.get(value);

                for (Method m : ms) {
                    m.setAccessible(true);
                    if (m.getName().toLowerCase().equals(name)) {
                        if (m.getReturnType() != Void.class) {
                            o = m.invoke(value);
                        }
                    }
                }
                obj.put(f.getName(), o);
            }
        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }

        return obj;
    }

}

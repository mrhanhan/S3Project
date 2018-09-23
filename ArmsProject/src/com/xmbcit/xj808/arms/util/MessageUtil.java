package com.xmbcit.xj808.arms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 消息显示工具类
 */
public class MessageUtil {
    private MessageUtil(){}

    /**
     * 消息弹出框在页面中弹出消息
     * @param msg
     * @param request
     */
    public static void toast(String msg, HttpServletRequest request){
        request.setAttribute("Toast",msg);
    }
    /**
     * 消息弹出框在页面中弹出消息
     * @param msg
     * @param request
     */
    public static void toast(String msg, HttpSession request){
        request.setAttribute("Toast",msg);
    }

    /**
     * 消息弹出框在页面中弹出消息
     * @param msg
     * @param request
     */
    public static void alert(String msg, HttpServletRequest request){
        request.setAttribute("Alert",msg);
    }
    /**
     * 消息弹出框在页面中弹出消息
     * @param msg
     * @param request
     */
    public static void alert(String msg, HttpSession request){
        request.setAttribute("Alert",msg);
    }

}

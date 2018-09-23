package com.xmbcit.xj808.arms.base.routing;


import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.xmbcit.xj808.arms.base.Controller;
import com.xmbcit.xj808.arms.util.CommonUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.Files;

import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 路由的Controller<br>
 * 例如根据不同过的访问路径，来进行不同的操作
 */
public abstract class RoutController extends Controller {
    /**
     * 路由方法
     */
    private List<RoutMethod> routMethods = null;


    @Override
    public void init() throws ServletException {
        super.init();
        /*获取类的方法信息，并提取出，处理路由的方法*/
        Class<?> conrollClass = getClass();
        routMethods = new ArrayList<>();
        paseMethod(conrollClass.getMethods());
    }

    /**
     * 解析方法
     *
     * @param methods
     */
    private void paseMethod(Method[] methods) {
        for (Method method : methods) {
            Rout r = method.getAnnotation(Rout.class);
            if (r != null && !CommonUtil.isNull(r.url())) {
                routMethods.add(new RoutMethod(method, r));
            }
        }
    }

    @Override
    protected final String forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }

    @Override
    protected String sendRedicte(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }


    @Override
    protected final void doHttp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI();
        String m = req.getMethod();
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        ServletContext context = getServletContext();
        ServletConfig config = getServletConfig();

        SmartUpload upload = new SmartUpload();
        Request request = null;
        Files files = null;

        for (RoutMethod rm : routMethods) {

            System.out.println(rm.isRequestMethon(m));
            if (requestUri.endsWith(rm.getUrl()) && rm.isRequestMethon(m)) {
                if (rm.isUpLoad()) {
                    upload.initialize(config,req,resp);
                    try {
                        upload.upload();
                    } catch (SmartUploadException e) {

                    }
                    request = upload.getRequest();
                    files = upload.getFiles();
                }
                if(rm.call(this, req, resp, out, session, context, config, upload, request, files)) {
                    out.close();
                    return;
                }
            }
        }

        defaultRequest(req, resp, out);
        out.close();
    }

    /**
     * 默认匹配路径<br/>如果请求的连接未配置路由
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected abstract void defaultRequest(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException;
}

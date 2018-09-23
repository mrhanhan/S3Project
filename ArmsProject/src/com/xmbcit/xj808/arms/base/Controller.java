package com.xmbcit.xj808.arms.base;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller基类
 */
public abstract class Controller extends HttpServlet {
    protected Logger log = Logger.getLogger(getClass());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHttp(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHttp(req,resp);
    }

    protected void doHttp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        super.service(req, resp);
        String url = forward(req, resp);
        if (url != null && !url.isEmpty()) {
            req.getRequestDispatcher(url).forward(req, resp);
            return;
        }
        url = sendRedicte(req, resp);
        if (url != null && !url.isEmpty()) {
            resp.sendRedirect(url);
            return;
        }

    }
    /**
     * 转发方法，执行完请求后，可以根据返回的连接进行转发，如果返回null不会进行转发
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }
    /**
     * 重定向方法，执行完请求后，可以根据返回的连接进行转发，如果返回null不会进行重定向
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    protected String sendRedicte(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }
}

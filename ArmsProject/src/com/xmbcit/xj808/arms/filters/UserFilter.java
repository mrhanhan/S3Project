package com.xmbcit.xj808.arms.filters;

import com.xmbcit.xj808.arms.base.HttpFilter;
import com.xmbcit.xj808.arms.entity.User;
import com.xmbcit.xj808.arms.util.MessageUtil;
import com.xmbcit.xj808.arms.util.TypeUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = {"/admin/*","/customer/*","/goods/*"})
public class UserFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = servletRequest.getSession();
        User u = TypeUtil.convert(session.getAttribute("LOGIN_USER"), User.class);
        if (u == null){
            MessageUtil.toast("请先登陆！",session);
            servletResponse.sendRedirect(servletRequest.getServletContext().getContextPath()+"/tools/comeback.jsp");
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}

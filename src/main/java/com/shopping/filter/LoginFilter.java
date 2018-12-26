package com.shopping.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        Object user = request.getSession().getAttribute("user");
        Object shop = request.getSession().getAttribute("shop");
        Object admin = request.getSession().getAttribute("admin");
        System.out.println("LoginFilter调用，获取到user="+user+",shop="+shop+",admin="+admin);
        String uri=request.getRequestURI();
        if (uri.contains("showImage")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (uri.contains("user")&&user==null){
            request.getSession().setAttribute("shop",null);
            request.getSession().setAttribute("admin",null);
        } else if (uri.contains("shop")&&shop==null){
            request.getSession().setAttribute("user",null);
            request.getSession().setAttribute("admin",null);
        } else if (uri.contains("admin")&&admin==null){
            request.getSession().setAttribute("user",null);
            request.getSession().setAttribute("shop",null);
        }
        /**
         * 对用户、商户、管理员是否登录进行验证
         */
        if (user!=null){
            if (uri.contains("user")){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                response.sendRedirect("/userLogin.jsp");
            }
        }
        else if (shop!=null){
            if (uri.contains("shop")){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                response.sendRedirect("/shopLogin.jsp");
            }
        }
        else if (admin!=null){
            if (uri.contains("admin")){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                response.sendRedirect("/adminLogin.jsp");
            }
        }
        else {
            //都为空
            if (uri.contains("login")||uri.contains("Login")||uri.contains("Register")||uri.contains("register")){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            } else {
                if (uri.contains("user"))response.sendRedirect("/userLogin.jsp");
                else if (uri.contains("shop"))response.sendRedirect("/shopLogin.jsp");
                else if (uri.contains("admin"))response.sendRedirect("/adminLogin.jsp");
                else response.sendRedirect("userLogin.jsp");//跳转到主页
            }
        }
    }

    @Override
    public void destroy() {
    }
}

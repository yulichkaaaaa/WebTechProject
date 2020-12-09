package com.yuliana.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;
        final HttpSession session = request.getSession();
        if(session.getAttribute("status") == null){
            response.sendRedirect(request.getContextPath() + "/login");
        }else{
            chain.doFilter(request,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}

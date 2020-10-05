package com.jmc.web.filter;

import com.jmc.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String url = req.getRequestURI();
        if (url.contains("success") && session.getAttribute("u") == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().print("<h1>请先登录！</h1>" +
                    "<a href=\"login.jsp\">登录</a>");
        } else {
            chain.doFilter(req, resp);
        }
    }
}

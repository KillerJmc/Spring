package com.jmc.web.filter;

import com.jmc.domain.Admin;
import com.jmc.domain.User;
import com.jmc.lang.Strs;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class SecureFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        System.out.println(req.getRequestURI());
        if (Strs.orContains(req.getRequestURI(), "index", "login", "register", "invalidAccess")
                || req.getRequestURI().equals(req.getContextPath() + "/")) {
            chain.doFilter(req, resp);
        } else {
            User u = (User) session.getAttribute("u");
            Admin a = (Admin) session.getAttribute("a");
            if (a != null) {
                out.print("<h4>管理员：" + a.getName() + "</h4>");
                chain.doFilter(req, resp);
            } else if (u != null) {
                out.print("<h4>用户：" + u.getName() + "</h4>");
                chain.doFilter(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/invalidAccess.jsp");
            }
        }
    }
}

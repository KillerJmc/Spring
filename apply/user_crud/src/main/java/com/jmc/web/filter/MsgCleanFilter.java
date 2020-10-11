package com.jmc.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class MsgCleanFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;

        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        chain.doFilter(req, resp);
        if (!req.getRequestURI().contains("Servlet"))
            if (session.getAttribute("msg") != null)
                session.removeAttribute("msg");

    }
}

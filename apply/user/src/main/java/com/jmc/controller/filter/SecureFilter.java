package com.jmc.controller.filter;

import com.jmc.common.ConstName;
import com.jmc.domain.Admin;
import com.jmc.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jmc
 */
@WebFilter(value = {"/func.html", "/viewAllUserServlet", "/modifyUserServlet", "/updateUserServlet", "/deleteUserServlet"})
public class SecureFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        var session = req.getSession();
        var out = response.getWriter();

        Admin a;
        User u;

        if ((a = (Admin) session.getAttribute(ConstName.ADMIN_SESSION_NAME)) != null) {
            out.printf(ConstName.BLUE_STR, "管理员：" + a.getName());
            chain.doFilter(req, resp);
        } else if ((u = (User) session.getAttribute(ConstName.USER_SESSION_NAME)) != null) {
            out.printf(ConstName.BLUE_STR, "用户：" + u.getName());
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect("invalidAccess.html");
        }
    }
}

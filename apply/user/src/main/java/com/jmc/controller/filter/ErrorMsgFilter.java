package com.jmc.controller.filter;

import com.jmc.common.ConstName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Jmc
 */
@WebFilter(value = {"/register.html", "/login.html", "/modifyUserServlet"})
public class ErrorMsgFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 必须在doFilter之前声明，才能有效输出
        var out = response.getWriter();
        var session = ((HttpServletRequest) request).getSession();

        chain.doFilter(request, response);

        var errorMsg = session.getAttribute("errorMsg");

        if (errorMsg != null) {
            out.printf(ConstName.RED_STR, errorMsg);
            session.removeAttribute("errorMsg");
        }
    }
}

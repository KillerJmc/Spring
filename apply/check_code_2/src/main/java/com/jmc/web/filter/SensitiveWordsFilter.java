package com.jmc.web.filter;

import com.jmc.lang.Strs;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        req.setCharacterEncoding("utf-8");

        String[] sensitiveWords = new String[] {
            "fuck", "ass", "slut", "bitch", "shit",
            "日", "操", "草", "艹", "尼玛", "你妈", "草泥马", "屁眼", "鸡巴", "祖宗", "先人"
        };

        if (req.getRequestURI().contains("verify")) {
            String name = req.getParameter("name").equals("") ? "null" : req.getParameter("name");
            if (Strs.orContains(name, sensitiveWords)) {
                session.setAttribute("errMsg",
                        "<br><h1>用户名存在敏感词汇！</h1>");
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }
        }
        chain.doFilter(req, resp);
    }
}

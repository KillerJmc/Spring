package com.jmc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

@WebServlet("/main")
public class mainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cs = req.getCookies();
        boolean isFirstTime = true;
        resp.setContentType("text/html;charset=utf-8");

        if (cs != null) {
            for (Cookie c : cs) {
                if (c.getName().equals("lastAccessTime")) {
                    resp.getWriter().println("欢迎回来，您上次的访问时间为：" +
                            c.getValue().replace("_", " "));
                    isFirstTime = false;
                }
            }
        }

        if (isFirstTime) resp.getWriter().println("您好，欢迎您首次访问！");

        Cookie c = new Cookie("lastAccessTime",
                LocalDateTime.now()
                        .toString()
                        .replace("T", "_")
                        .substring(0, 19)
        );
        resp.addCookie(c);
    }
}

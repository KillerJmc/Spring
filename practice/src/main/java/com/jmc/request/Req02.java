package com.jmc.request;

import com.jmc.io.Streams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/req2")
public class Req02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String referer = req.getHeader("referer");
        if (referer != null && referer.contains("/p")) {
            System.out.println("正常渠道进入！");
            System.out.println("地址：" + referer);
            resp.getWriter().print("正常渠道进入！<br>");
        } else {
            System.out.println("非法入侵！");
            System.out.println("地址：" + referer);
            resp.getWriter().print("非法入侵！<br>");
        }
        resp.getWriter().print("地址：" + referer + "<br>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String parms = Streams.readToStr(req.getInputStream());
        resp.getWriter().print(parms + "<br>");
        System.out.println(parms);
    }
}

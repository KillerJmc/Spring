package com.jmc.request;

import com.jmc.io.Streams;
import com.jmc.lang.Outs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/req2")
public class RequestDemo2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("referer");
        //设置编码
        response.setContentType("text/html;charset=utf-8");

        if (referer != null && referer.contains("/ser")) {
            response.getWriter().println("正常渠道进入！");
        } else {
            response.getWriter().println("非法入侵者！");
        }
        response.getWriter().println("网站：" + referer + "\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
//        BufferedReader br = req.getReader();
        resp.getWriter().println(Streams.readToStr(req.getInputStream()));
    }
}

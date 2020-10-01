package com.jmc.request;

import com.jmc.lang.Tries;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/req3")
public class Req03 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        System.out.println(req.getMethod());
        resp.getWriter().print(req.getMethod() + "<br>");
        req.getParameterMap().forEach((k, v) -> {
            System.out.println(k + " : " + Arrays.toString(v));
            Tries.tryThis(() -> resp.getWriter().print(k + " : " + Arrays.toString(v) + "<br>"));
        });
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

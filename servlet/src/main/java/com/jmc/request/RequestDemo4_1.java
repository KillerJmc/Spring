package com.jmc.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/req4_1")
public class RequestDemo4_1 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo4_1被访问了");
        resp.getWriter().println("demo4_1...");

//        RequestDispatcher rd = req.getRequestDispatcher("/req4_2");
//        rd.forward(req, resp);


        //转发的特点：
        //1.浏览器地址路径不发生变化
        //2.只能转发到当前服务器内部资源中
        //3.转发是一次请求


        //request域：代表一次请求的范围，一般用于请求转发的多个资源中共享数据
        //储存数据到request域中
        req.setAttribute("msg", "hello");

        req.getRequestDispatcher("/req4_2")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

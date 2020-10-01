package com.jmc.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContext5")
public class ServletContextDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();

        //获取文件的服务器真实路径
        String path = context.getRealPath("/a.txt"); //webapp目录下资源访问
        System.out.println(path);

        //WEB-INF目录下资源访问
        System.out.println(context.getRealPath("/WEB-INF/b.txt"));
        //src目录下资源访问
        System.out.println(context.getRealPath("/WEB-INF/classes/com/jmc/context/c.txt"));
    }
}

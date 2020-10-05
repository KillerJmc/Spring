package com.jmc.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContext1")
public class ServletContextDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ServletContext用于所有用户间共享数据，服务器为每个web项目只创建一个此对象
        //本质是在一个web应用所有servlet对象间共享数据
        ServletContext sc1 = req.getServletContext();
        ServletContext sc2 = this.getServletContext();
        System.out.println(sc1);
        System.out.println(sc2);
        System.out.println(sc1 == sc2);
    }
}

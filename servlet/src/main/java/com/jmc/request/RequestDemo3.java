package com.jmc.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/req3")
public class RequestDemo3 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决post控制台乱码问题（get没此问题）
        req.setCharacterEncoding("utf-8");


        resp.setContentType("text/html;charset=utf-8");

        //get, post均通用
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
        resp.getWriter().println("用户名：" + username);
        resp.getWriter().println("密码：" + password);

//        String[] usernames = req.getParameterValues("username");
//
//        Enumeration<String> parameterNames = req.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String[] values = req.getParameterValues(parameterNames.nextElement());
//            System.out.println(parameterNames.nextElement() + " : "
//                    + Arrays.toString(values));
//        }
//
//        Map<String, String[]> parameterMap = req.getParameterMap();
//        parameterMap.forEach((k, v) -> System.out.println(k +
//                Arrays.toString(v)));
    }
}

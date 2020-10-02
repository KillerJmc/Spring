package com.jmc.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/session2")
public class SessionDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取到的session和demo1的session是同一个
        HttpSession session = req.getSession();
        Object msg = session.getAttribute("msg");
        System.out.println(msg);
        resp.getWriter().println("session: " + session);
//        //销毁session
//        session.invalidate();

//        //设置session不活跃最长时间:单位分钟（该时间默认30分钟，30分钟不活跃对象即被销毁）
//        session.setMaxInactiveInterval(30);
    }
}

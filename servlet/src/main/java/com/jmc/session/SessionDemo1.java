package com.jmc.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

//session: 服务器会话技术，储存数据在服务器端的对象中
/*
    Session的实现依赖于Cookie（作用范围与其默认值相同：此web项目，周期默认也相同：浏览器关闭cookie消失）
    原理：首次若不存在JSESSIONID的cookie，则用getSession()创建一个session对象，
    并利用set-cookie技术设置一个JSESSIONID的cookie，
    里面存放该session对象的ID，当其他servlet再次获取session时，就会利用cookie
    获得该session的ID，并凭借此从内存中获取session对象
*/
@WebServlet("/session1")
public class SessionDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        //使客户端关闭后，session也能相同
        Cookie c = new Cookie("JSESSIONID", session.getId());
        c.setMaxAge(60 * 60);
        resp.addCookie(c);

        session.setAttribute("msg", "hello session!");
        resp.getWriter().println("session: " + session);
    }
}

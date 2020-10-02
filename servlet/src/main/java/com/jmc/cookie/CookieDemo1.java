package com.jmc.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieDemo1")
public class CookieDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //持久性：浏览器关闭后才失效
        //作用范围：只能作用于当前目录（.）及其子目录，默认不能作用于上一级目录
        Cookie c1 = new Cookie("msg", "你好");
        Cookie c2 = new Cookie("name", "Jmc");

        //设置cookie存活时间
        //负数：默认持久性
        //正数：写入硬盘，储存正数秒后删除（关闭浏览器后仍有效）
        //零：直接删除
        c1.setMaxAge(10);

        //使能在服务器所有项目内共享：使支持作用于根目录（默认是null(/ser)：当前目录)
        c1.setPath("/");
        c2.setPath("/");
        /*
            c2.setDomain(".ping.com");
            子域名可以读父域名中的cookie，如在.ping.com可以共享
            p1.ping.com, p2.ping.com ...

            本例默认domain为：localhost
        */


        resp.addCookie(c1);
        resp.addCookie(c2);
    }
}

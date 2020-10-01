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
        Cookie c1 = new Cookie("msg", "你好");
        Cookie c2 = new Cookie("name", "Jmc");

        //设置cookie存活时间
        //负数：默认持久性
        //正数：写入硬盘，储存正数秒后删除（关闭浏览器后仍有效）
        //零：直接删除
        c1.setMaxAge(10);

        //使能在服务器所有项目内共享（默认是/ser)
        c1.setPath("/");
        c2.setPath("/");
        /*
            c2.setDomain(".baidu.com");
            那么tieba.baidu.com和news.baidu.com中cookie可以共享
            （不同服务器间共享）
         */


        resp.addCookie(c1);
        resp.addCookie(c2);
    }
}

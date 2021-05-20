package com.jmc.request;


import com.jmc.lang.extend.Outs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/req1")
public class RequestDemo1 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Outs.newLine(() -> {
            System.out.println(req.getMethod()); // GET
            //*获取虚拟目录
            System.out.println(req.getContextPath()); // /ser
            System.out.println(req.getServletPath()); // /req1
            System.out.println(req.getQueryString()); // username=jmc
            System.out.println(req.getRequestURI()); // /ser/req1
            System.out.println(req.getRequestURL().toString()); // http://localhost/ser/req1
            //获取协议和版本
            System.out.println(req.getProtocol()); // HTTP/1.1
            System.out.println(req.getRemoteAddr()); // 0:0:0:0:0:0:0:1
        });

        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            var key = headerNames.nextElement();
            System.out.println(key + " : " + req.getHeader(key));
        }
        Outs.newLine();
    }
}

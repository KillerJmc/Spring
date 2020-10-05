package com.jmc.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

//@WebServlet(value = "/demo")
@WebServlet("/demo1")
public class ServletDemo1 implements Servlet {
    /*
        初始化方法，在Servlet对象创建时（用户访问servlet）执行，且只执行一次。（Servlet对象是单例的！）
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...");
    }

    /*
        获取ServletConfig对象
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
        提供服务方法，每次servlet被访问的时候都会被执行
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service...");
    }

    /*
        获取Servlet信息
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /*
        销毁方法，交代遗言，在Servlet对象被销毁之前时（服务器正常停止时）执行，且只执行一次，一般用于释放资源（只有服务器正常关闭时才会被执行）
    */
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}

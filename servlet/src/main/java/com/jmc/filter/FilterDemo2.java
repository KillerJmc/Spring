package com.jmc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 注解的filter执行规则：按类字符串：值小的先执行
 * @author Jmc
 */

//dispatcherTypes：被分发过滤器的类型：请求，转发，错误等
//只有请求，转发某到某页面时才会被拦截
@WebFilter(value = "*.html", dispatcherTypes = {DispatcherType.REQUEST})
public class FilterDemo2 implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //对req请求消息做增强
        System.out.println("filter2 running...");
        chain.doFilter(req, resp);
        //对resp响应消息做增强
        System.out.println("filter2 came back...");
    }

    //服务器启动时filter对象被启动时被调用，只执行一次
    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init...");
    }

    //服务器正常关闭时filter对象被销毁时被调用，只执行一次
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }

}

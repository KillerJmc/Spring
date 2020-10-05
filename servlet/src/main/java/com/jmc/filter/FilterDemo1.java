package com.jmc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


//注解中 value属性 相当于 urlPatterns属性
//@WebFilter("/*") //访问所有资源之前，都会执行该过滤器
//也可为/user/*, *.jsp, *.html
public class FilterDemo1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter...");

        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

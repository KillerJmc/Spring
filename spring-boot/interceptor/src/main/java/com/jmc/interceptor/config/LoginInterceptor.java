package com.jmc.interceptor.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        if ("666".equals(req.getParameter("password"))) {
            return true;
        } else {
            resp.getWriter().write("fail!");
            return false;
        }
    }
}

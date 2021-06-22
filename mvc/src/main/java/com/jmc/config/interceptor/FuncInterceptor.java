package com.jmc.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FuncInterceptor implements HandlerInterceptor {
    /**
     * 执行方法之前拦截
     * @return 只有返回true时候，postHandle和afterCompletion才会被执行，不然直接不执行方法，卡着。
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        System.out.println("FuncInterceptor.preHandle");

        var session = req.getSession();
        resp.setContentType("text/html;charset=utf-8");

        var user = session.getAttribute("u");
        if (user == null) {
            resp.getWriter().println("您还没登录！");
            resp.setHeader("refresh","2;url=login.html");
            return false;
        }

        return true;
    }

    /**
     * 执行方法后，返回视图前拦截
     */
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("FuncInterceptor.postHandle");
    }

    /**
     * 返回视图后拦截
     */
    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) throws Exception {
        System.out.println("FuncInterceptor.afterCompletion");
    }
}

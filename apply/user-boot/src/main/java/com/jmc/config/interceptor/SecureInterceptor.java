package com.jmc.config.interceptor;

import com.jmc.common.Const;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecureInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        var session = req.getSession();

        if (session.getAttribute(Const.LOGGED_IN_SESSION) == null) {
            resp.sendRedirect("invalidAccess.html");
            return false;
        }
        return true;
    }
}

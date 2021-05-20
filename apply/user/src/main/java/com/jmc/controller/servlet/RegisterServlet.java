package com.jmc.controller.servlet;

import com.jmc.common.ConstName;
import com.jmc.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jmc
 */
@WebServlet("/registerServlet")
@Controller("registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        var session = req.getSession();

        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String password = req.getParameter("password");
        String errorMsg = name.isBlank() ? "姓名为空！" :
                age.isBlank() ? "年龄为空！" :
                password.isBlank() ? "密码为空！" :
                userService.containsUser(name) ? "注册失败，用户名已存在！" : "";

        if (errorMsg.isBlank()) {
            userService.addUser(name, Integer.parseInt(age), password);
            out.println("<h1>注册成功！3秒后将帮您自动登录</h1>");
            session.setAttribute(ConstName.USER_SESSION_NAME, userService.getUser(name, password));
            // 跳转到功能界面
            resp.setHeader("refresh","3;url=func.html?seed=" + System.currentTimeMillis());
        } else {
            session.setAttribute("errorMsg", errorMsg);
            // 重定向到注册界面
            resp.sendRedirect("register.html" + "?seed=" + System.currentTimeMillis());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

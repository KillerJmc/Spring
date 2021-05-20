package com.jmc.controller.servlet;

import com.jmc.common.ConstName;
import com.jmc.domain.Admin;
import com.jmc.domain.User;
import com.jmc.service.AdminService;
import com.jmc.service.UserService;
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
@WebServlet("/loginServlet")
@Controller("loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    private AdminService adminService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var out = resp.getWriter();

        if (session.getAttribute(ConstName.ADMIN_SESSION_NAME) != null
                || session.getAttribute(ConstName.USER_SESSION_NAME) != null) {
            out.println("请勿重复登录，正在为您跳转到功能界面");
            resp.setHeader("refresh","2;url=func.html?seed=" + System.currentTimeMillis());
            return;
        }

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        String errorMsg = name.isBlank() ? "用户名为空" :
                password.isBlank() ? "密码为空" : "";

        if (!errorMsg.isBlank()) {
            session.setAttribute("errorMsg", errorMsg);
            // 重定向到注册界面
            resp.sendRedirect("login.html?seed=" + System.currentTimeMillis());
            return;
        }

        Admin a;
        User u;
        if ((a = adminService.getAdmin(name, password)) != null) {
            session.setAttribute("admin", a);
            // 重定向到功能界面
            resp.sendRedirect("func.html?seed=" + System.currentTimeMillis());
        } else if ((u = userService.getUser(name, password)) != null) {
            session.setAttribute("user", u);
            // 重定向到功能界面
            resp.sendRedirect("func.html?seed=" + System.currentTimeMillis());
        } else {
            session.setAttribute("errorMsg", "用户名或密码错误");
            // 重定向到注册界面
            resp.sendRedirect("login.html?seed=" + System.currentTimeMillis());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

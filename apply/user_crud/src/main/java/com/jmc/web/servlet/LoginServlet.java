package com.jmc.web.servlet;

import com.jmc.domain.Admin;
import com.jmc.domain.User;
import com.jmc.service.AdminService;
import com.jmc.service.UserService;
import com.jmc.service.impl.AdminServiceImpl;
import com.jmc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        Map<String, String[]> map = req.getParameterMap();
        UserService userService = new UserServiceImpl();
        AdminService adminService = new AdminServiceImpl();

        if (map.get("name")[0].isEmpty()) {
            session.setAttribute("msg", "用户名不能为空!");
            resp.sendRedirect(req.getContextPath());
            return;
        }

        session.removeAttribute("a");
        session.removeAttribute("u");

        User u = new User(map.get("name")[0], map.get("password")[0]);
        Admin a = new Admin(map.get("name")[0], map.get("password")[0]);
        if (adminService.contains(a)) {
            session.setAttribute("msg", "登录成功，管理员：" +
                    a.getName() + "，欢迎您！");
            session.setAttribute("a", a);
        } else if (userService.contains(u)) {
            session.setAttribute("msg", "登录成功，" +
                    u.getName() + "欢迎您！");
            session.setAttribute("u", userService.getByName(u.getName()));
        } else {
            session.setAttribute("msg", "登录失败，用户名或密码错误！");
        }
        resp.sendRedirect(req.getContextPath());
    }
}

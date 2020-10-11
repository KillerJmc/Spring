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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> map = req.getParameterMap();
        UserService userService = new UserServiceImpl();
        HttpSession session = req.getSession();

        if (map.get("name")[0].isEmpty()) {
            session.setAttribute("msg", "用户名不能为空！");
            resp.sendRedirect(req.getContextPath());
            return;
        }

        User u = new User(map.get("name")[0], map.get("password")[0], map.get("gender")[0]);
        if (userService.containsName(u.getName())) {
            session.setAttribute("msg", "注册失败，已存在该用户名");
        } else {
            userService.add(u);
            session.removeAttribute("a");
            session.setAttribute("msg", "注册成功，已为您自动登录！");
            session.setAttribute("u", userService.getByName(u.getName()));
        }
        resp.sendRedirect(req.getContextPath());
    }
}

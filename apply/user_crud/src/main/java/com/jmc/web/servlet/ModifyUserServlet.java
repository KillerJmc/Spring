package com.jmc.web.servlet;

import com.jmc.domain.User;
import com.jmc.lang.Bools;
import com.jmc.lang.Objs;
import com.jmc.service.UserService;
import com.jmc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/modifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        Map<String, String[]> map = req.getParameterMap();
        if (Objs.nullOrEmpty(map.get("id")[0], map.get("name")[0])) {
            session.setAttribute("msg", "id和姓名不能为空！");
            req.getRequestDispatcher("modifyUser.jsp").forward(req, resp);
            return;
        }

        User u = new User(Integer.parseInt(map.get("id")[0]),
                map.get("name")[0],
                map.get("password")[0],
                map.get("gender")[0]);

        UserService userService = new UserServiceImpl();
        if (session.getAttribute("a") != null) {
            session.setAttribute("msg", userService.update(u) ? "修改成功！" : "修改失败！");
        } else {
            if (u.getId() != ((User) session.getAttribute("u")).getId()) {
                session.setAttribute("msg", "修改失败，权限不足！您只能修改自己的用户信息");
            } else {
                boolean success;
                session.setAttribute("msg", (success = userService.update(u)) ? "修改成功！" : "修改失败！");
                if (success) session.removeAttribute("u");
            }
        }
        req.getRequestDispatcher("modifyUser.jsp").forward(req, resp);
    }
}

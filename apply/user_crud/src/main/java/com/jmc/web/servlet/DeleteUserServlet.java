package com.jmc.web.servlet;

import com.jmc.domain.User;
import com.jmc.service.UserService;
import com.jmc.service.impl.UserServiceImpl;
import com.jmc.utils.NumUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        Map<String, String[]> map = req.getParameterMap();
        String idOrName = map.get("idOrName")[0];
        UserService userService = new UserServiceImpl();

        if (idOrName.isEmpty()) {
            session.setAttribute("msg", "id或姓名不能为空！");
            req.getRequestDispatcher("deleteUser.jsp").forward(req, resp);
            return;
        }
        if (NumUtils.isNumber(idOrName)) {
            int id = Integer.parseInt(idOrName);
            if (session.getAttribute("a") != null) {
                session.setAttribute("msg", userService.deleteById(id) ?
                    "删除成功！" : "删除失败！");
            } else {
                if (id != ((User) session.getAttribute("u")).getId()) {
                    session.setAttribute("msg", "删除失败，权限不足！您只能删除自己的用户信息");
                } else {
                    boolean success;
                    session.setAttribute("msg", (success = userService.deleteById(id)) ?
                            "删除成功！" : "删除失败！");
                    if (success) session.removeAttribute("u");
                }
            }
        } else {
            if (session.getAttribute("a") != null) {
                userService.deleteByName(idOrName);
            } else {
                if (!idOrName.equals(((User) session.getAttribute("u")).getName())) {
                    session.setAttribute("msg", "删除失败，权限不足！您只能删除自己的用户信息");
                } else {
                    boolean success;
                    session.setAttribute("msg", (success = userService.deleteByName(idOrName)) ?
                        "删除成功！" : "删除失败！");
                    if (success) session.removeAttribute("u");
                }
            }
        }

        req.getRequestDispatcher("deleteUser.jsp").forward(req, resp);
    }
}

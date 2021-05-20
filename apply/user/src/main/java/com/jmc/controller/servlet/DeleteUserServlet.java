package com.jmc.controller.servlet;

import com.jmc.common.ConstName;
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
@WebServlet("/deleteUserServlet")
@Controller("deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var out = resp.getWriter();

        if (session.getAttribute(ConstName.USER_SESSION_NAME) != null) {
            out.printf("<br>" + ConstName.RED_STR, "删除失败！普通用户没有权利删除用户");
            resp.setHeader("refresh", "2;url=modifyUserServlet");
            return;
        }

        String idStr = req.getParameter("id");
        String name = req.getParameter("name");

        if (idStr.isBlank() && name.isBlank()) {
            session.setAttribute("errorMsg", "要删除的id和姓名不能全为空");
            resp.sendRedirect("modifyUserServlet");
            return;
        } else if (!idStr.isBlank() && !name.isBlank()) {
            session.setAttribute("errorMsg", "要删除的id和姓名不能全部填满");
            resp.sendRedirect("modifyUserServlet");
            return;
        } else if (idStr.isBlank()) {
            if (userService.getUserByName(name) == null) {
                out.printf("<br>" + ConstName.RED_STR, "删除失败！要删除的用户名不存在");
                resp.setHeader("refresh","2;url=modifyUserServlet");
                return;
            }
            userService.deleteUserByName(name);
        } else if (name.isBlank()) {
            int id = Integer.parseInt(idStr);
            if (userService.getUserById(id) == null) {
                out.printf("<br>" + ConstName.RED_STR, "删除失败！要删除的用户id不存在");
                resp.setHeader("refresh","2;url=modifyUserServlet");
                return;
            }
            userService.deleteUserById(id);
        }
        out.printf("<br>" + ConstName.RED_STR, "删除成功！正在返回修改用户界面");
        resp.setHeader("refresh","2;url=modifyUserServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

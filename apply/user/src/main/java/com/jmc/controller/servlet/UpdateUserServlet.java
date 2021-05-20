package com.jmc.controller.servlet;

import com.jmc.common.ConstName;
import com.jmc.domain.User;
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
@WebServlet("/updateUserServlet")
@Controller("updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
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

        String idStr = req.getParameter("id");
        String newName = req.getParameter("newName");
        String newAgeStr = req.getParameter("newAge");
        String newPassword = req.getParameter("newPassword");

        String errorMsg = idStr.isBlank() ? "被更新的id为空" :
                newName.isBlank() ? "新姓名为空" :
                newAgeStr.isBlank() ? "新年龄为空" :
                newPassword.isBlank() ? "新密码为空" : "";

        if (!errorMsg.isBlank()) {
            session.setAttribute("errorMsg", errorMsg);
            resp.sendRedirect("modifyUserServlet");
            return;
        }

        int id = Integer.parseInt(idStr);
        User operatingUser;
        // 如果是用户在操作
        if ((operatingUser = (User) session.getAttribute(ConstName.USER_SESSION_NAME)) != null) {
            if (id != operatingUser.getId()) {
                out.printf("<br>" + ConstName.RED_STR, "修改失败！普通用户只能修改自己的信息");
            } else {
                int newAge = Integer.parseInt(newAgeStr);
                userService.updateUserById(id, newName, newAge, newPassword);
                session.setAttribute(ConstName.USER_SESSION_NAME, new User(id, newName, newAge, newPassword));
                out.println("<br>修改成功！正在返回修改用户界面");
            }
            resp.setHeader("refresh","2;url=modifyUserServlet");
            return;
        }

        // 如果是管理员在操作
        User updatedUser = userService.getUserById(id);
        if (updatedUser == null) {
            out.printf("<br>" + ConstName.RED_STR, "修改失败！被修改的用户不存在");
            resp.setHeader("refresh","2;url=modifyUserServlet");
            return;
        }

        if (!updatedUser.getName().equals(newName) && userService.containsUser(newName)) {
            session.setAttribute("errorMsg", "用户名已存在，修改失败");
            resp.sendRedirect("modifyUserServlet");
            return;
        }

        int newAge = Integer.parseInt(newAgeStr);
        userService.updateUserById(id, newName, newAge, newPassword);
        out.println("<br>修改成功！正在返回修改用户界面");
        resp.setHeader("refresh","2;url=modifyUserServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

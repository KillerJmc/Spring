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
@WebServlet("/viewAllUserServlet")
@Controller("viewAllUserServlet")
public class ViewAllUsersServlet extends HttpServlet {
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

        if (session.getAttribute(ConstName.ADMIN_SESSION_NAME) != null) {
            out.println(userService.getAllUsersAsString());
        } else {
            out.println(userService.getAllUsersWithoutPasswordAsString());
        }
        
        out.println("""
        <br><br>
        <a href="func.html" style="font-size:20px;">返回主菜单</a>
        """);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

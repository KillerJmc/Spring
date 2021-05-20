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
@WebServlet("/modifyUserServlet")
@Controller("modifyUserServlet")
public class ModifyUserServlet extends HttpServlet {
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

        out.println("<h1 style=\"text-align:center\">修改用户信息</h1>");

        if (session.getAttribute(ConstName.ADMIN_SESSION_NAME) != null) {
            out.println(userService.getAllUsersAsString());
        } else {
            out.println(userService.getAllUsersWithoutPasswordAsString());
        }

        out.println("""
        <br><br>
        <pre style="font-size:20px;color:#FF0000">
        提示：
        1）修改：通过输入用户的id，并输入用户的新姓名，新年龄，新密码，最后点击修改按钮。
        2）删除：通过输入用户的id或姓名（仅选一样），最后点击删除按钮。
        </pre>
        
        <br><br>
        
        <form action="updateUserServlet" method="post">
            <label>
                <input name="id" type="text" style="font-size:15px" placeholder="id"><br>
                <input name="newName" type="text" style="font-size:15px" placeholder="新姓名"><br>
                <input name="newAge" type="text" style="font-size:15px" placeholder="新年龄"><br>
                <input name="newPassword" type="text" style="font-size:15px" placeholder="新密码"><br>
                <input type="submit" style="font-size:15px" value="修改"/>
            </label>
        </form>
        
        <br><br>
        
        <form action="deleteUserServlet" method="post">
            <label>
                <input name="id" type="text" style="font-size:15px" placeholder="id"><br>
                <input name="name" type="text" style="font-size:15px" placeholder="姓名"><br>
                <input type="submit" style="font-size:15px" value="删除"/>
            </label>
        </form>
        
        <br><br>
        
        <a href="func.html" style="font-size:20px;">返回主菜单</a>
        
        <br>
        """);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}

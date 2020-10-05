package com.jmc.web.servlet;

import com.jmc.dao.impl.UserDaoImpl;
import com.jmc.domain.User;
import com.jmc.service.UserService;
import com.jmc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/verifyServlet")
public class VerifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();

        Map<String, String[]> parameterMap = req.getParameterMap();
        String RealCheckCode = (String) session.getAttribute("checkCode");

        if (parameterMap != null && parameterMap.size() > 0) {
            String checkCode = parameterMap.get("checkCode")[0];
            String name = parameterMap.get("name")[0];
            String password = parameterMap.get("password")[0];

            String t = name.equals("") ? "用户名" :
                    password.equals("") ? "密码" :
                            checkCode.equals("") ? "验证码" : null;
            if (t != null) {
                session.setAttribute("errMsg","<h1>" + t + "不能为空" + "</h1>");
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }

            if (!checkCode.equals(RealCheckCode)) {
                session.setAttribute("errMsg","<h1>验证码错误！</h1>");
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            } else {
                UserService userService = new UserServiceImpl();
                User u = new User(name, password);
                if (!userService.containsUser(u)) {
                    session.setAttribute("errMsg","<h1>用户名或密码错误！</h1>");
                    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                } else {
                    req.getSession().setAttribute("u", u);
                    resp.sendRedirect(req.getContextPath() + "/successServlet");
                }
            }
        }
    }
}

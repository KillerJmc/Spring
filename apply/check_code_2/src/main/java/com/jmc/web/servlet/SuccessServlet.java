package com.jmc.web.servlet;

import com.jmc.domain.User;

import javax.imageio.metadata.IIOMetadataNode;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        out.print("<h1>" + ((User) session.getAttribute("u")).getName() + "，欢迎您！</h1>");
        out.print(" <a href=\"login.jsp\">返回</a>");

        session.removeAttribute("u");
        session.removeAttribute("checkCode");
    }
}

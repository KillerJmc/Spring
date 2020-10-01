package com.jmc.servlet;

import com.jmc.io.Files;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String fileName =req.getParameter("fileName");
        String filePath = context.getRealPath("img/" + fileName);
        resp.setContentType(context.getMimeType(fileName));
        if (req.getParameter("download").equals("true"))
            //encode防止中文文件名乱码
            resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        Files.out(filePath, resp.getOutputStream(), true);
        System.out.println(URLEncoder.encode("哈哈.jpg", StandardCharsets.UTF_8));
    }
}

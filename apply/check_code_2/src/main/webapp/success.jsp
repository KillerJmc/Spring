<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结果</title>
</head>
<body>
    <%
        if (request.getHeader("referer") != null) {
            if (request.getHeader("referer").contains("login.jsp")) {
                out.print("<h1>" + request.getSession().getAttribute("username") + "，欢迎您！</h1>");
                out.print(" <a href=\"login.jsp\">返回</a>");
            }
        } else {
            out.print("<h1>请勿越界访问！</h1>");
        }

        session.removeAttribute("username");
        session.removeAttribute("checkCode");
    %>
</body>
</html>

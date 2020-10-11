<%@ page import="com.jmc.service.impl.UserServiceImpl" %>
<%@ page import="com.jmc.service.impl.AdminServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看用户信息</title>
</head>
<body>
    <h2>所有用户信息如下：</h2><br>
    <pre>
        <%= new UserServiceImpl().getAllText(session.getAttribute("a") != null) %>
    </pre><br>
    <a href="funcMenu.jsp">返回</a>
</body>
</html>

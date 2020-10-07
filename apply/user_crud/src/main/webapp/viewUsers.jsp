<%@ page import="com.jmc.service.UserService" %>
<%@ page import="com.jmc.service.impl.UserServiceImpl" %>
<%@ page import="com.jmc.service.AdminService" %>
<%@ page import="com.jmc.service.impl.AdminServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看用户信息</title>
</head>
<body>
    <h2>所有用户信息如下：</h2><br>
    <pre>
        <%= session.getAttribute("a") != null ?
                new AdminServiceImpl().getAllText() :
                    new UserServiceImpl().getAllText() %>
    </pre><br>
    <a href="funcMenu.jsp">返回</a>
</body>
</html>

<%@ page import="com.jmc.service.UserService" %>
<%@ page import="com.jmc.service.impl.UserServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除用户信息</title>
</head>
<body>
    <h2>所有用户信息如下：</h2><br>
    <pre>
        <%= new UserServiceImpl().getAllText(false) %>
    </pre><br>
    <form method="post" action="deleteUserServlet">
        <input type="text" placeholder="请输入用户的id或姓名" name="idOrName">
        <input type="submit" value="删除">
    </form>

    ${msg}
    <a href="funcMenu.jsp">返回</a>
</body>
</html>

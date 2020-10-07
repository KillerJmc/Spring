<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form method="post" action="loginServlet">
        <input type="text" placeholder="请输入用户名" name="name">
        <input type="password" placeholder="请输入密码" name="password">
        <input type="submit" value="登录">
    </form>
    <a href="index.jsp">返回</a>
</body>
</html>

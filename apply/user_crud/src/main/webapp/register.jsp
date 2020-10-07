<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <form method="post" action="registerServlet">
        <input type="text" placeholder="请输入用户名" name="name">
        <input type="password" placeholder="请输入密码" name="password">
        <input type="text" placeholder="请输入性别" name="gender">
        <input type="submit" value="注册">
    </form>
    <a href="index.jsp">返回</a>
</body>
</html>

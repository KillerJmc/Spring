<%@ page import="com.jmc.service.UserService" %>
<%@ page import="com.jmc.service.impl.UserServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息</title>
</head>
<body>
    <h2>所有用户信息如下：</h2><br>
    <pre>
        <%
            UserService userService = new UserServiceImpl();
            out.write(userService.getAllText());
        %>
    </pre><br>
    <form method="post" action="modifyUserServlet">
        <input type="text" placeholder="修改用户的id" name="id">
        <input type="text" placeholder="修改后用户名" name="name">
        <input type="text" placeholder="修改后密码" name="password">
        <input type="text" placeholder="修改后性别" name="gender">
        <input type="submit" value="修改">
    </form>

    ${msg}
    <% session.removeAttribute("msg"); %>
    <a href="funcMenu.jsp">返回</a>
</body>
</html>

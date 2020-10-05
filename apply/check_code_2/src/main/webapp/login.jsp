<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script>
        window.onload = function () {
            const img = document.getElementById("checkCodeServlet");
            img.onclick = function () {
                const date = new Date().getTime();
                img.src = "/cc2/checkCodeServlet?" + date;
            }
        }
    </script>
</head>
<body>
    <form action="verifyServlet" method="post">
        <input type="text" name="name" placeholder="请输入用户名"/>
        <input type="password" name="password" placeholder="请输入密码"/>
        <input type="text" name="checkCode" placeholder="请输入验证码"/>
        <input type="submit" value="登录"/>
    </form>

    <img src="checkCodeServlet" id="checkCodeServlet">
    <a href="login.jsp">看不清换一张?</a>

    <%--打印session中储存的错误信息--%>
    ${errMsg}
    <% session.removeAttribute("errMsg"); %>
</body>
</html>

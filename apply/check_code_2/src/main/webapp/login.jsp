<%@ page import="java.util.Map" %>
<%@ page import="com.jmc.domain.User" %>
<%@ page import="com.jmc.dao.UserDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script>
        window.onload = function () {
            const img = document.getElementById("checkCodeImage");
            img.onclick = function () {
                const date = new Date().getTime();
                img.src = "/cc2/checkCodeImage.jsp?" + date;
            }
        }
    </script>
</head>
<body>
    <form action="login.jsp" method="post">
        <input type="text" name="username" placeholder="请输入用户名"/>
        <input type="password" name="password" placeholder="请输入密码"/>
        <input type="text" name="checkCode" placeholder="请输入验证码"/>
        <input type="submit" value="登录"/>
    </form>

    <img src="checkCodeImage.jsp" id="checkCodeImage">
    <a href="login.jsp">看不清换一张?</a>

    <%
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();
        String RealCheckCode = (String) session.getAttribute("checkCode");

        if (parameterMap != null && parameterMap.size() > 0) {
            String checkCode = parameterMap.get("checkCode")[0];
            String username = parameterMap.get("username")[0];
            String password = parameterMap.get("password")[0];

            String t = username.equals("") ? "用户名" :
                password.equals("") ? "密码" :
                    checkCode.equals("") ? "验证码" : null;
            if (t != null) {
                out.println("<h1>" + t + "不能为空" + "</h1>");
                return;
            }

            if (!checkCode.equals(RealCheckCode)) {
                out.println("<h1>验证码错误！</h1>");
            } else {
                User u = new UserDaoImpl().getUserByName(username);
                if (u == null || !u.getPassword().equals(password)) {
                    out.println("<h1>用户名或密码错误！</h1>");
                } else {
                    request.getSession().setAttribute("username", username);
                    response.sendRedirect(request.getContextPath() + "/success.jsp");
                }
            }
        }
    %>
</body>
</html>

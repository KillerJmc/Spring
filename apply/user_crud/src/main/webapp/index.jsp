<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
    <%
        /*
         * 项目：账号系统
         * 启动时间：2020.10.6
         * 更新：
         *      10.6:
         *              19:26 - 20:10
         *              20:20 - 21:54
         *      10.7
         *              09:22 - 11:43
         *              13:24 - 13:44
         *              13:50 - 16:08
         * 共耗时：7小时17分钟
         *      10.8    1. 加入MsgCleanFilter
         *              2. 把部分jsp改写成html
         *              3. 减少对jsp中逻辑代码的依赖
         */
    %>

    <h1>账号系统</h1>
    <h3>请选择以下功能：</h3>
    <a href="register.html">注册</a><br>
    <a href="login.html">登录</a><br>
    <a href="funcMenu.jsp">用户信息</a><br><br>

    <h2>${msg}</h2>
</body>
</html>

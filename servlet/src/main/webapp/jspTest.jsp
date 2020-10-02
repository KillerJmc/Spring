<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSPTest</title>
</head>
<body>
    <%--定义在service方法中--%>
    <%
        System.out.println("java: service");
    %>

    <%--定义成员变量--%>
    <%!
        public int i = 3;
    %>

    <%--输出到页面中--%>
    <%--实质：out.print( "i = " + i );--%>
    <%= "i = " + i %>

    <br>

    <%--使用内置对象--%>
    <%
        String contextPath = request.getContextPath();
        out.print(contextPath);
    %>

    <%--resp一定会先于out.print输出，会输出到页面最前面--%>
    <%--tomcat真正给客户端做出相应之前，会先找resp缓冲区数据，再找out缓冲区数据--%>
    <%--建议只用out--%>
    <%
        response.getWriter().println("final?<br>");
    %>
</body>
</html>

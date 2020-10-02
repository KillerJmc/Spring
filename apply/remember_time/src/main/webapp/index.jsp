<%@ page import="java.time.LocalDateTime" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <%
        Cookie[] cs = request.getCookies();
        boolean isFirstTime = true;
        if (cs != null)  {
            for (Cookie c : cs) {
                if (c.getName().equals("lastAccessTime")) {
                    response.getWriter().println("欢迎回来，您上次的访问时间为：" +
                            c.getValue().replace("_", " "));
                    isFirstTime = false;
                }
            }
        }

        if (isFirstTime) response.getWriter().println("您好，欢迎您首次访问！");

        Cookie c = new Cookie("lastAccessTime",
                LocalDateTime.now()
                        .toString()
                        .replace("T", "_")
                        .substring(0, 19)
        );
        response.addCookie(c);
    %>

</body>
</html>

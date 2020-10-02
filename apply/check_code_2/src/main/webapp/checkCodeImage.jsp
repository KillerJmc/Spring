<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.util.Random" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page contentType="image/jpeg" language="java" %>
<html>
<head>
    <title>CheckCode</title>
</head>
<body>
    <%
        int width = 100, height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(Color.PINK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width - 1, height - 1);
        String t = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random r = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int idx = r.nextInt(t.length());
            char c = t.charAt(idx);
            sb.append(c);
            g.drawString(c + "", width / 5 * i, height /2);
        }

        request.getSession().setAttribute("checkCode", sb.toString());

        g.setColor(Color.GREEN);
        for (int i = 0; i < 10; i++) {
            int x1 = r.nextInt(width);
            int x2 = r.nextInt(width);
            int y1 = r.nextInt(height);
            int y2 = r.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", bos);
        response.getOutputStream().write(bos.toByteArray());
    %>
</body>
</html>

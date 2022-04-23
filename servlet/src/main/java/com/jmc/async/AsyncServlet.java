package com.jmc.async;


import com.jmc.lang.extend.Outs;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

@WebServlet(value = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 开启异步获取上下文对象
        var asyncContext = req.startAsync(req, resp);

        System.out.println("start: " + LocalDateTime.now());

        // 执行异步任务（Servlet 3.0）
        asyncContext.start(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 提示任务完成，返回给前端
            asyncContext.complete();
        });

        // NIO Servlet 3.1
        try {
            var out = resp.getOutputStream();
            out.setWriteListener(new WriteListener() {
                @Override
                public void onWritePossible() throws IOException {
                    out.write("ok".getBytes());
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("NIO: onError");
                    throw new RuntimeException(t);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end: " + LocalDateTime.now());
    }
}

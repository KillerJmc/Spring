package com.jmc.listener;


import com.jmc.lang.extend.Tries;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;

@WebListener
public class ServletLoaderListener implements ServletContextListener {
    //服务器启动被调用
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //加载资源文件
        ServletContext context = sce.getServletContext();
        String res = context.getInitParameter("res");
        String realPath = context.getRealPath(res);
        Tries.tryThis(() -> System.out.println(new FileInputStream(new File(realPath))));
        System.out.println("ServletLoaderListener.contextInitialized");
    }
    //服务器正常结束被销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletLoaderListener.contextDestroyed");
    }
}

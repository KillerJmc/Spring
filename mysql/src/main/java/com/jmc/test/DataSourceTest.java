package com.jmc.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest {
    public static void main(String[] args) throws Exception {
        var app = new ClassPathXmlApplicationContext("applicationContext.xml");
        var ds = app.getBean(DruidDataSource.class);

        var conn = ds.getConnection();
        System.out.println(conn);
        conn.close();
    }
}

package com.jmc.web;

import com.alibaba.druid.pool.DruidDataSource;
import com.jmc.config.SpringConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class DataSourceTest {
    public static void main(String[] args) throws Exception {
//        var app = new ClassPathXmlApplicationContext("applicationContext.xml");
        var app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        var ds = app.getBean(DataSource.class);
        var conn = ds.getConnection();
        System.out.println(conn);
        conn.close();
    }
}

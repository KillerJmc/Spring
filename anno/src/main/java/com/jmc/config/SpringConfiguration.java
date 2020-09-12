package com.jmc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

//标志此类是Spring的核心配置类
@Configuration
@ComponentScan("com.jmc")
//可以同时导入多个，用逗号隔开
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {

}

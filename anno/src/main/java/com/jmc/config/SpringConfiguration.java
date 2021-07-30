package com.jmc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//标志此类是Spring的核心配置类
@Configuration
@ComponentScan("com.jmc")
//可以同时导入多个，用逗号隔开
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {

}

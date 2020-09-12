package com.jmc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.jmc")
@EnableAspectJAutoProxy //启动自动代理
public class SpringConfig {
}

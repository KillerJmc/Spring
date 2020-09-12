package com.jmc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.jmc")
@Import(JdbcConfig.class)
public class SpringConfig {
}

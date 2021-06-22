package com.jmc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(value = "com.jmc", excludeFilters = @ComponentScan.Filter(Controller.class))
@Import(JdbcConfig.class)
public class SpringConfig {
}

package com.jmc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Jmc
 */
@Configuration
@ComponentScan("com.jmc")
@Import(JbdcConfig.class)
public class SpringConfig {

}

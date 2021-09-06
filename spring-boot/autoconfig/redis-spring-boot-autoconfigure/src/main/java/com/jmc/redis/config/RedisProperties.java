package com.jmc.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

// 配置文件（可在application.yml中编写相关配置）
@ConfigurationProperties("redis")
@Data
public class RedisProperties {
    private String host = "localhost";
    private int port = 6379;
}

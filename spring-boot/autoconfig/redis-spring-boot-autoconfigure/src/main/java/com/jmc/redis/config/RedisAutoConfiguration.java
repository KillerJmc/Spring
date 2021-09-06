package com.jmc.redis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

// 声明为配置类
@Configuration
// 启动配置文件（同时导入pom坐标以激活用户配置追踪）
@EnableConfigurationProperties(RedisProperties.class)
// 仅有导入Jedis坐标才执行
@ConditionalOnClass(Jedis.class)
public class RedisAutoConfiguration {
    @Bean("jedis")
    // 仅在不存在该名称的bean时才导入
    @ConditionalOnMissingBean(name = "jedis")
    public Jedis jedis(RedisProperties redisProperties) {
        return new Jedis(redisProperties.getHost(), redisProperties.getPort());
    }
}

package com.jmc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
public class RedisConfig {
    /**
     * 通用代码，提前创建RedisCacheConfiguration，并指定json序列化方式<br>
     * 其在RedisCacheConfiguration.determineConfiguration(...)起作用
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        System.out.println("json!");
        // 指定json序列化方式
        return RedisCacheConfiguration.defaultCacheConfig().
                serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}

package com.jmc.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * test
 */
@SpringBootTest
public class RedisTest {
    /**
     * K: 查询的key类型
     * V: 查询的value类型
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testSet() {
        redisTemplate.boundValueOps("name").set("王五");
    }

    @Test
    public void testGet() {
        System.out.println(redisTemplate.boundValueOps("name").get());
    }
}

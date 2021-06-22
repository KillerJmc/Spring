package com.jmc.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class MyRedisTestApplication {
    public static void main(String[] args) {
        var app = SpringApplication.run(MyRedisTestApplication.class, args);
        var jedis = app.getBean(Jedis.class);
        System.out.println(jedis);
    }
}

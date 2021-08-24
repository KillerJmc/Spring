package com.jmc.ack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitAckApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitAckApplication.class, args);
    }
}

package com.jmc.confirm.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "queue")
    public void receive(String msg) {
        log.info("consumer: receive a msg <- {}", msg);
    }
}

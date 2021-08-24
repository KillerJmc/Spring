package com.jmc.delay.consumer;

import com.jmc.delay.config.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = Const.DEAD_QUEUE)
    public void get(String msg) {
        log.info("dead queue: get message <- {}", msg);
    }
}

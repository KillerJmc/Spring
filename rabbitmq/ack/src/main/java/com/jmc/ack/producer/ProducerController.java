package com.jmc.ack.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController {
    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{msg}")
    public void send(@PathVariable String msg) {
        rabbitTemplate.convertAndSend("exg", "", msg, message -> {
            message.getMessageProperties().setDeliveryTag(123);
            return message;
        });
        log.info("Producer: 发送一条消息 -> {}", msg);
    }
}

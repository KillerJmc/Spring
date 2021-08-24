package com.jmc.delay.producer;

import com.jmc.delay.config.Const;
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
        rabbitTemplate.convertAndSend(Const.NORMAL_EXG, Const.NORMAL_ROUTING, msg);
        log.info("normal queue: send message -> {}, delay time -> {}ms", msg, Const.DELAY_TIME);
    }
}

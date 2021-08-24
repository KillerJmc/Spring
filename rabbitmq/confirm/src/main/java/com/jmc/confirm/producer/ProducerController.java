package com.jmc.confirm.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController {
    private final RabbitTemplate rabbitTemplate;
    private final AtomicInteger id = new AtomicInteger();

    @GetMapping("/send/{msg}")
    public void send(@PathVariable String msg) throws InterruptedException {
        // 将在ConfirmCallback中获取到
        var returned = new ReturnedMessage(new Message(msg.getBytes()), 0, null, "exg", "routing");

        var correlationData = new CorrelationData("producer-" + id.incrementAndGet());
        correlationData.setReturned(returned);

        var correlationData2 = new CorrelationData("producer-" + id.incrementAndGet());
        correlationData2.setReturned(returned);

        var correlationData3 = new CorrelationData("producer-" + id.incrementAndGet());
        correlationData3.setReturned(returned);

        var correlationData4 = new CorrelationData("producer-" + id.incrementAndGet());
        correlationData4.setReturned(returned);

        rabbitTemplate.convertAndSend("exg", "routing", msg, correlationData);
        log.info("producer: 正常发送消息 -> “{}”", msg);

        TimeUnit.SECONDS.sleep(1);

        rabbitTemplate.convertAndSend("exg2", "routing", msg, correlationData2);
        log.info("producer: 交换机宕机 -> “{}”", msg);

        TimeUnit.SECONDS.sleep(1);

        rabbitTemplate.convertAndSend("exg", "routing2", msg, correlationData3);
        log.info("producer: 队列宕机 -> “{}”", msg);

        TimeUnit.SECONDS.sleep(1);

        rabbitTemplate.convertAndSend("exg2", "routing2", msg, correlationData4);
        log.info("producer: 交换机和队列都宕机 -> “{}”", msg);
    }
}

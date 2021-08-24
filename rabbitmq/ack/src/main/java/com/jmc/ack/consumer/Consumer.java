package com.jmc.ack.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "queue")
    public void receive(String msg, Channel channel, Message message) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        if (msg.contains("what")) {
            channel.basicReject(deliveryTag, false);
            log.warn("Consumer: 收到消息：{}，但消息非法，因此拒绝接收！", msg);
        } else {
            channel.basicAck(deliveryTag, false);
            log.info("Consumer: 成功收到消息 <- {}", msg);
        }
    }
}

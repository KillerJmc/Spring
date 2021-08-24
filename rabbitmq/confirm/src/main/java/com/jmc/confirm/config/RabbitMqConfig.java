package com.jmc.confirm.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RabbitMqConfig {
    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        // 发布确认
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            // 由生产者传入，不会为空
            assert correlationData != null;
            var id = correlationData.getId();
            var returned = correlationData.getReturned();

            // 由生产者定义，不会为空
            assert returned != null;
            var msg = new String(returned.getMessage().getBody());
            var exg = returned.getExchange();
            var routing = returned.getRoutingKey();

            if (ack) {
                log.info("confirmCallBack: 编号为：“{}” 的消息：“{}” 发送到交换机：“{}” 成功！下一步将通过路由：“{}” 发送到特定队列",
                        id, msg, exg, routing);
            } else {
                log.error("confirmCallBack: 编号为：“{}” 的消息：“{}” 发送到交换机：“{}” 失败！原因是：“{}”",
                        id, msg, exg, cause);
            }
        });

        // 返回确认
        rabbitTemplate.setReturnsCallback(returned -> {
            var msg = new String(returned.getMessage().getBody());
            var exg = returned.getExchange();
            var routing = returned.getRoutingKey();
            var replyCode = returned.getReplyCode();
            var replyText = returned.getReplyText();

            log.error("returnsCallback: 消息：“{}” 从交换机：“{}” 发送到路由：“{}” 失败！返回码：“{}”，返回信息：“{}”",
                    msg, exg, routing, replyCode, replyText);
        });
    }

    @Bean
    public DirectExchange exg() {
        return ExchangeBuilder.directExchange("exg").build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("queue").autoDelete().build();
    }

    @Bean
    public Binding bindQueueToExg(Queue queue, DirectExchange exg) {
        return BindingBuilder.bind(queue).to(exg).with("routing");
    }
}

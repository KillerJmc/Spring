package com.jmc.delay.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public DirectExchange normalExchange() {
        return ExchangeBuilder.directExchange(Const.NORMAL_EXG).build();
    }

    @Bean
    public DirectExchange deadExchange() {
        return ExchangeBuilder.directExchange(Const.DEAD_EXG).build();
    }

    @Bean
    public Queue normalQueue() {
        return QueueBuilder.durable(Const.NORMAL_QUEUE)
                .ttl(Const.DELAY_TIME)
                .deadLetterExchange(Const.DEAD_EXG)
                .deadLetterRoutingKey(Const.DEAD_ROUTING)
                .autoDelete()
                .build();
    }

    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(Const.DEAD_QUEUE)
                .autoDelete()
                .build();
    }

    @Bean
    public Binding normalBind(Queue normalQueue, DirectExchange normalExchange) {
        return BindingBuilder.bind(normalQueue).to(normalExchange).with(Const.NORMAL_ROUTING);
    }

    @Bean
    public Binding deadBind(Queue deadQueue, DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(Const.DEAD_ROUTING);
    }
}

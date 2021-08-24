package com.jmc.ack.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public FanoutExchange exg() {
        return ExchangeBuilder.fanoutExchange("exg").build();
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("queue").autoDelete().build();
    }

    @Bean
    public Binding bindQueueToExg(FanoutExchange exg, Queue queue) {
        return BindingBuilder.bind(queue).to(exg);
    }
}

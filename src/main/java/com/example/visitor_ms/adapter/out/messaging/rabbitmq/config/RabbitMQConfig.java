package com.example.visitor_ms.adapter.out.messaging.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    private static final String QUEUE_NAME = "EVENTS_QUEUE";
    private static final String EXCHANGE_NAME = "amq.direct";

    @Bean
    Queue queue() {
        System.out.println("chamou queue");
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    DirectExchange exchange() {
        System.out.println("chamou exchange");
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        System.out.println("chamou binding");
        return BindingBuilder.bind(queue).to(exchange).with(queue.getName());
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        Queue eventBus = this.queue();
        DirectExchange exchange = this.exchange();
        Binding binding = this.binding(eventBus, exchange);

        rabbitAdmin.declareQueue(eventBus);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareBinding(binding);

        return rabbitAdmin;
    }
}

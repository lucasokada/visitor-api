package com.example.visitor_ms.port.out.messaging.rabbit;

import com.example.visitor_ms.domain.dto.EventDto;
import com.example.visitor_ms.domain.messaging.EventWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQEventPublisher implements EventWriter {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Override
    public void publish(EventDto<?> event) {
        rabbitTemplate.convertAndSend(queue.getName(), event);
    }
}

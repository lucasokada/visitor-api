package com.example.visitor_ms.domain.messaging;

import com.example.visitor_ms.domain.dto.EventDto;

public interface EventWriter {
    void publish(EventDto<?> event);
}

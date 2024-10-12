package com.example.visitor_ms.port.in.rest.person;

import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.command.CreateVisitorCommand;
import com.example.visitor_ms.domain.service.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/person")
@RequiredArgsConstructor
public class PersonController {
    private final VisitorService visitorService;

    @PostMapping
    public Visitor create(CreateVisitorCommand request) {
        return visitorService.create(request);
    }
}

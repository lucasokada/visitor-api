package com.example.visitor_ms.port.in.rest.person;

import com.example.visitor_ms.domain.Person;
import com.example.visitor_ms.domain.command.CreatePersonCommand;
import com.example.visitor_ms.domain.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public Person create(CreatePersonCommand request) {
        return personService.create(request);
    }
}

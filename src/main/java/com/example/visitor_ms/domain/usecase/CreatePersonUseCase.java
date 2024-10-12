package com.example.visitor_ms.domain.usecase;

import com.example.visitor_ms.domain.Person;
import com.example.visitor_ms.domain.command.CreatePersonCommand;

public interface CreatePersonUseCase {
    Person create(CreatePersonCommand personCommand);
}

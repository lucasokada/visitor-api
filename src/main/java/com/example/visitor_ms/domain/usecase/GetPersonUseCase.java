package com.example.visitor_ms.domain.usecase;

import com.example.visitor_ms.domain.Person;

import java.util.Optional;

public interface GetPersonUseCase {
    Optional<Person> getByDocumentNumber(String documentNumber);
}

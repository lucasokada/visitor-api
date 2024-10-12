package com.example.visitor_ms.domain.repository;

import com.example.visitor_ms.domain.Person;

import java.util.Optional;

public interface PersonRepository {
    void save(Person person);
    Optional<Person> findByDocumentNumber(String documentNumber);
}

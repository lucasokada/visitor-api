package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.Individual;
import com.example.visitor_ms.domain.Person;
import com.example.visitor_ms.domain.command.CreatePersonCommand;
import com.example.visitor_ms.domain.repository.PersonRepository;
import com.example.visitor_ms.domain.usecase.CreatePersonUseCase;
import com.example.visitor_ms.domain.usecase.GetPersonUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PersonService implements CreatePersonUseCase, GetPersonUseCase {
    private final PersonRepository personRepository;

    @Override
    public Person create(CreatePersonCommand personCommand) {
        Optional<Person> existentPerson = personRepository.findByDocumentNumber(personCommand.getDocumentNumber());
        if(existentPerson.isPresent()) {
            return existentPerson.get();
        } else {
            Person person = new Individual(personCommand.getName(), personCommand.getDocumentNumber(), personCommand.getBornIn());
            personRepository.save(person);
            return person;
        }
    }

    @Override
    public Optional<Person> getByDocumentNumber(String documentNumber) {
        return personRepository.findByDocumentNumber(documentNumber);
    }
}

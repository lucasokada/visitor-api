package com.example.visitor_ms.adapter.in.person;

import com.example.visitor_ms.domain.Individual;
import com.example.visitor_ms.domain.Person;
import com.example.visitor_ms.domain.repository.PersonRepository;
import com.example.visitor_ms.port.in.db.repository.person.PersonEntity;
import com.example.visitor_ms.port.in.db.repository.person.PersonJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {
    private final PersonJpaRepository personJpaRepository;

    @Override
    public void save(Person person) {
        PersonEntity entity = new PersonEntity(person);
        personJpaRepository.save(entity);
    }

    @Override
    public Optional<Person> findByDocumentNumber(String documentNumber) {
        Optional<PersonEntity> person = personJpaRepository.findByDocumentNumber(documentNumber);

        return Optional.of(new Individual(person.get().getName(), person.get().getDocumentNumber(), null));
    }
}

//1 - event source
//2 - resiliencia bd
//3 - persitencia em lote
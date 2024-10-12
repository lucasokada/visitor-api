package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.Individual;
import com.example.visitor_ms.domain.Person;
import com.example.visitor_ms.domain.command.CreatePersonCommand;
import com.example.visitor_ms.domain.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {
    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    private CreatePersonCommand validPersonCommand() {
        return new CreatePersonCommand("Augusto João da Rosa", "11309929939", LocalDate.of(1959, 3, 20));
    }

    @Test
    void create_whenSuccess_expectReturnPerson() {
        CreatePersonCommand request = validPersonCommand();
        Person person = new Individual("Augusto João da Rosa", "11309929939", LocalDate.of(1959, 3, 20));
        when(personRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());

        Person result = personService.create(request);

        assertEquals(person, result);
        verify(personRepository).save(person);
    }

    @Test
    void create_whenExistentPerson_expectReturnPerson() {
        CreatePersonCommand request = validPersonCommand();
        Person person = new Individual("Camila Eliane Marina Farias", "11309929939", LocalDate.of(1960, 3, 5));
        when(personRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(person));

        Person result = personService.create(request);

        assertEquals(person, result);
        verify(personRepository, never()).save(any(Person.class));
    }

    @Test
    void create_whenSaveThrowException_expectThrowException() {
        CreatePersonCommand request = validPersonCommand();
        Person person = new Individual("Augusto João da Rosa", "11309929939", LocalDate.of(1959, 3, 20));
        when(personRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());
        doThrow(RuntimeException.class).when(personRepository).save(any(Person.class));

        assertThrows(RuntimeException.class, () -> personService.create(request));

        verify(personRepository).save(person);
    }
}

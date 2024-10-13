package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidPersonException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IndividualTest {
    private Contact validContact = new Contact("6930138401", "9626759827", "1935794707", "name@email.com");
    private Access validAccess = new Access("userName", "PassworD123!");
    private Set<Address> validAddresses = new HashSet<>() {{
        add(new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)", "13506517", "Rio Claro", BrazilState.SP));
        add(new Address("Avenida 7", 773, "Jardim Claret", "13503255", "Rio Claro", BrazilState.SP));
    }};

    @Test
    void individualPerson_whenValid_expectCreateNewPerson() {
        assertDoesNotThrow(() -> new Individual("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), validContact, validAccess, validAddresses));
    }

    @Test
    void individualPerson_whenOnlyFirstName_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Individual("Augusto João da Rosa 2",
                "11309929939", LocalDate.of(1959, 3, 20), validContact, validAccess, validAddresses));
    }

    @Test
    void individualPerson_whenNameWithNumbers_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Individual("Augusto", "11309929939",
                LocalDate.of(1959, 3, 20), validContact, validAccess, validAddresses));
    }

    @Test
    void individualPerson_whenAgeIsGreaterThan120_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Individual("Augusto João da Rosa",
                "11309929939", LocalDate.of(1900, 3, 20), validContact, validAccess, validAddresses));
    }

    @Test
    void individualPerson_whenInvalidDocumentNumberLength_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Individual("Augusto João da Rosa",
                "1130992993", LocalDate.of(1959, 3, 20), validContact, validAccess, validAddresses));
    }

    @Test
    void individualPerson_whenInvalidDocumentNumberWithLetters_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Individual("Augusto João da Rosa",
                "a130992992a", LocalDate.of(1959, 3, 20), validContact, validAccess, validAddresses));
    }
}

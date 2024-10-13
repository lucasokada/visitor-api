package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidPersonException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VisitorTest {
    private Contact validContact = new Contact("6930138401", "9626759827", "1935794707", "name@email.com");
    private Access validAccess = new Access("userName", "PassworD123!");

    @Test
    void visitor_whenValid_expectCreateNewPerson() {
        assertDoesNotThrow(() -> new Visitor("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact, validAccess));
    }

    @Test
    void visitor_whenOnlyFirstName_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Visitor("Augusto João da Rosa 2",
                "11309929939", LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact, validAccess));
    }

    @Test
    void visitor_whenNameWithNumbers_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Visitor("Augusto", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact, validAccess));
    }

    @Test
    void visitor_whenAgeIsGreaterThan120_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Visitor("Augusto João da Rosa",
                "11309929939", LocalDate.of(1900, 3, 20), VisitorType.RELATED, validContact, validAccess));
    }

    @Test
    void visitor_whenInvalidDocumentNumberLength_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Visitor("Augusto João da Rosa",
                "1130992993", LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact, validAccess));
    }

    @Test
    void visitor_whenInvalidDocumentNumberWithLetters_expectInvalidPersonException() {
        assertThrows(InvalidPersonException.class, () -> new Visitor("Augusto João da Rosa",
                "a130992992a", LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact, validAccess));
    }
}

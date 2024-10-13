package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidLegalPersonException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LegalTest {
    @Test
    void legal_whenValidLegalPerson_expectCreateNewLegal() {
        assertDoesNotThrow(() -> new Legal("Universidade Estadual Paulista \"Júlio de Mesquita Filho\"", "48031918001872"));
    }

    @Test
    void legal_whenLegalPersonNameLengthIsLesserThan3_expectInvalidLegalPersonException() {
        assertThrows(InvalidLegalPersonException.class, () -> new Legal("UN", "48031918001872"));
    }

    @Test
    void legal_whenLegalPersonDocumentNumberLengthIsLesserThan14_expectInvalidLegalPersonException() {
        assertThrows(InvalidLegalPersonException.class, () -> new Legal("Universidade Estadual Paulista \"Júlio de Mesquita Filho\"", "4803191800187"));
    }

    @Test
    void legal_whenLegalPersonDocumentNumberIsGreaterThan14_expectInvalidLegalPersonException() {
        assertThrows(InvalidLegalPersonException.class, () -> new Legal("Universidade Estadual Paulista \"Júlio de Mesquita Filho\"", "480319180018721"));
    }

    @Test
    void legal_whenLegalPersonDocumentNumberWithLetters_expectInvalidLegalPersonException() {
        assertThrows(InvalidLegalPersonException.class, () -> new Legal("Universidade Estadual Paulista \"Júlio de Mesquita Filho\"", "a803191800187a"));
    }
}

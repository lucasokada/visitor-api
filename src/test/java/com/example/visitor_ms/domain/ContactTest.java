package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidContactException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContactTest {
    @Test
    void contact_whenValidContact_expectCreateNewContact() {
        assertDoesNotThrow(() -> new Contact("6930138401", "9626759827", "1935794707", "name@email.com"));
    }

    @Test
    void contact_whenInvalidPhoneLength_expectInvalidContactException() {
        assertThrows(InvalidContactException.class, () -> new Contact("693013840", "9626759827", "1935794707", "name@email.com"));
    }

    @Test
    void contact_whenInvalidPhoneWithLetters_expectInvalidContactException() {
        assertThrows(InvalidContactException.class, () -> new Contact("a93013840", "a626759827", "193579470a", "name@email.com"));
    }

    @Test
    void contact_whenInvalidCellPhoneLength_expectInvalidContactException() {
        assertThrows(InvalidContactException.class, () -> new Contact("6930138401", "9626759827", "193579470", "name@email.com"));
    }

    @Test
    void contact_whenInvalidEmail_expectInvalidContactException() {
        assertThrows(InvalidContactException.class, () -> new Contact("6930138401", "9626759827", "1935794707", "name.email.com"));
    }
}

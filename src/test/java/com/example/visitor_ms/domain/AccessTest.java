package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidAccessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccessTest {
    @Test
    void access_whenValid_expectCreateNewAccesss() {
        assertDoesNotThrow(() -> new Access("userName", "PassworD123!"));
    }

    @Test
    void access_whenUsernameLengthIsLesserThan3_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("usr", "PassworD123!"));
    }

    @Test
    void access_whenUsernameLengthIsGreaterThan20_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("testUserGreaterThan20", "PassworD123!"));
    }

    @Test
    void access_whenUsernameWithBlankSpace_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("user 1", "PassworD123!"));
    }

    @Test
    void access_whenUsernameSpecialCharacters_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("user1!", "PassworD123!"));
    }

    @Test
    void access_whenUsernameIsOnlyNumeric_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("123456", "PassworD123!"));
    }

    @Test
    void access_whenPasswordLengthIsLesserThan8_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("userName", "Pass1!"));
    }

    @Test
    void access_whenPasswordLengthIsGreaterThan20_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("userName", "passwordGreaterThan20"));
    }

    @Test
    void access_whenPasswordWithOnlyLowercase_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("userName", "pass"));
    }

    @Test
    void access_whenPasswordWithOnlyUppercase_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("userName", "PASS"));
    }

    @Test
    void access_whenPasswordWithoutNumbers_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("userName", "PasS"));
    }

    @Test
    void access_whenPasswordWithoutSpecialCharacters_expectInvalidAccessException() {
        assertThrows(InvalidAccessException.class, () -> new Access("userName", "PasS1"));
    }
}

package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidAddressException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {
    @Test
    void address_whenValidAddress_expectCreateNewAddress() {
        assertDoesNotThrow(() -> new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)",
                "13506517", "Rio Cláro", BrazilState.SP));
    }

    @Test
    void address_whenNegativeNumber_expectInvalidAddressException() {
        assertThrows(InvalidAddressException.class, () -> new Address("Rua 18 JB", -533, "Jardim Bandeirante (COHAB)",
                "13506517", "Rio Claro", BrazilState.SP));
    }

    @Test
    void address_whenZipCodeLengthIsLesserThan8_expectInvalidAddressException() {
        assertThrows(InvalidAddressException.class, () -> new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)",
                "1350651", "Rio Claro", BrazilState.SP));
    }

    @Test
    void address_whenZipCodeLengthIsLesserGreaterThan8_expectInvalidAddressException() {
        assertThrows(InvalidAddressException.class, () -> new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)",
                "135065171", "Rio Claro", BrazilState.SP));
    }

    @Test
    void address_whenZipCodeWithLetters_expectInvalidAddressException() {
        assertThrows(InvalidAddressException.class, () -> new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)",
                "a350651a", "Rio Claro", BrazilState.SP));
    }

    @Test
    void address_whenCityWithNumbers_expectInvalidAddressException() {
        assertThrows(InvalidAddressException.class, () -> new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)",
                "13506517", "Rio Claro 1", BrazilState.SP));
    }
}

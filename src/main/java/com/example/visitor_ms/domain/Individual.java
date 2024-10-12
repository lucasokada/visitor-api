package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidPersonException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
@ToString
public class Individual implements Person {
    private String name;
    private String documentNumber;
    private LocalDate bornAt;

    public Individual(String name, String documentNumber, LocalDate bornAt) {
        this.name = validateName(name);
        this.documentNumber = validateDocumentNumber(documentNumber);
        this.bornAt = validateBirthDate(bornAt);
    }

    private String validateName(String name) {
        if (name == null || !nameMatchesPattern(name) || !hasSecondName(name)) {
            throw new InvalidPersonException("Invalid name: it must contain only letters and have at least two parts.");
        }
        return name;
    }

    private boolean nameMatchesPattern(String name) {
        return name.matches("^[a-zA-ZÀ-ÿ\\s'-]+$");
    }

    private boolean hasSecondName(String name) {
        String[] nameParts = name.split("\\s+");
        return nameParts.length > 1;
    }

    private LocalDate validateBirthDate(LocalDate bornAt) {
        if (bornAt == null || !isValidBirthDate(bornAt)) {
            throw new InvalidPersonException("Invalid birth date: age must be less than 120 years.");
        }
        return bornAt;
    }

    private boolean isValidBirthDate(LocalDate bornAt) {
        LocalDate today = LocalDate.now();
        return today.getYear() - bornAt.getYear() < 120;
    }

    private String validateDocumentNumber(String documentNumber) {
        if (documentNumber == null || !documentNumber.matches("\\d{11}")) {
            throw new InvalidPersonException("Invalid document number: it must be 12 numeric characters.");
        }
        return documentNumber;
    }
}

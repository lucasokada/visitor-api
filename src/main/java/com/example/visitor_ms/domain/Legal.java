package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidLegalPersonException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Legal implements Person {
    private String name;
    private String documentNumber;

    public Legal(String name, String documentNumber) {
        this.name = validateName(name);
        this.documentNumber = validateDocumentNumber(documentNumber);
    }

    private String validateName(String name) {
        if (name.length() < 3) {
            throw new InvalidLegalPersonException("Invalid name: name must be at least 3 characters long.");
        }

        return name;
    }

    private String validateDocumentNumber(String documentNumber) {
        if (documentNumber == null || !documentNumber.matches("\\d{14}")) {
            throw new InvalidLegalPersonException("Invalid document number: CNPJ must be 14 numeric characters.");
        }

        return documentNumber;
    }
}

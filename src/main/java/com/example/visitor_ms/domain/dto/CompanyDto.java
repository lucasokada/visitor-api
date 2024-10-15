package com.example.visitor_ms.domain.dto;

import java.io.Serializable;

public record CompanyDto(
        String companyName,
        String documentNumber
) implements Serializable {
}

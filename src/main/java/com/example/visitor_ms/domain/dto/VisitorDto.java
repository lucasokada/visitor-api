package com.example.visitor_ms.domain.dto;

import com.example.visitor_ms.domain.VisitorType;

import java.io.Serializable;
import java.time.LocalDate;

public record VisitorDto(
        String name,
        String documentNumber,
        LocalDate bornAt,
        VisitorType visitorType
) implements Serializable {
}

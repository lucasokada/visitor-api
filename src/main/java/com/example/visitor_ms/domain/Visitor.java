package com.example.visitor_ms.domain;

import java.time.LocalDate;

public class Visitor extends Individual {
    private VisitorType type;

    public Visitor(String name, String documentNumber, LocalDate bornAt, VisitorType type) {
        super(name, documentNumber, bornAt);
        this.type = type;
    }
}

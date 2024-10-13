package com.example.visitor_ms.domain;

import java.time.LocalDate;
import java.util.Set;

public class Visitor extends Individual {
    private VisitorType type;

    public Visitor(String name, String documentNumber, LocalDate bornAt, VisitorType type, Contact contact, Access access, Set<Address> addresses) {
        super(name, documentNumber, bornAt, contact, access, addresses);
        this.type = type;
    }
}

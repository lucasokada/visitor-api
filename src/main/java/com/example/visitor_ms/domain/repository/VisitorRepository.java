package com.example.visitor_ms.domain.repository;

import com.example.visitor_ms.domain.Visitor;

import java.util.Optional;

public interface VisitorRepository {
    void save(Visitor person);
    Optional<Visitor> findByDocumentNumber(String documentNumber);
}

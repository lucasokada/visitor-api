package com.example.visitor_ms.domain.repository;

import com.example.visitor_ms.domain.Visitor;

import java.util.Optional;
import java.util.Set;

public interface VisitorRepository {
    void save(Visitor person);
    Optional<Visitor> findByDocumentNumber(String documentNumber);
    boolean existsByDocumentNumber(String documentNumber);
    void deleteByDocumentNumber(String documentNumber);
    Set<Visitor> findAllById(Iterable<String> documentNumbers);
}

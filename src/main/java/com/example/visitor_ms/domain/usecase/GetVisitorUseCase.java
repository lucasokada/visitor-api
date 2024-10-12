package com.example.visitor_ms.domain.usecase;

import com.example.visitor_ms.domain.Visitor;

import java.util.Optional;

public interface GetVisitorUseCase {
    Optional<Visitor> getByDocumentNumber(String documentNumber);
}

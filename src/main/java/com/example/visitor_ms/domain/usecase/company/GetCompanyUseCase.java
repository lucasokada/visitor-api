package com.example.visitor_ms.domain.usecase.company;

import com.example.visitor_ms.domain.Company;

import java.util.Optional;

public interface GetCompanyUseCase {
    Optional<Company> getByDocumentNumber(String documentNumber);
}

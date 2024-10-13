package com.example.visitor_ms.domain.repository;

import com.example.visitor_ms.domain.Company;

import java.util.Optional;

public interface CompanyRepository {
    void save(Company company);
    Optional<Company> findByDocumentNumber(String documentNumber);
}

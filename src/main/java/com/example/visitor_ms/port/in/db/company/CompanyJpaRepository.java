package com.example.visitor_ms.port.in.db.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, String> {
    Optional<CompanyEntity> findByDocumentNumber(String documentNumber);
}

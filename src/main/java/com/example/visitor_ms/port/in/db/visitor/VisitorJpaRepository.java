package com.example.visitor_ms.port.in.db.visitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorJpaRepository extends JpaRepository<IndividualPersonEntity, String> {
    Optional<VisitorEntity> findByDocumentNumber(String documentNumber);
    boolean existsByDocumentNumber(String documentNumber);
    void deleteByDocumentNumber(String documentNumber);
}

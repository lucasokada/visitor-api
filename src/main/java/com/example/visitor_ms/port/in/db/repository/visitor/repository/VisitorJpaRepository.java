package com.example.visitor_ms.port.in.db.repository.visitor.repository;

import com.example.visitor_ms.port.in.db.repository.visitor.entity.IndividualPersonEntity;
import com.example.visitor_ms.port.in.db.repository.visitor.entity.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorJpaRepository extends JpaRepository<IndividualPersonEntity, String> {
    Optional<VisitorEntity> findByDocumentNumber(String documentNumber);
    boolean existsByDocumentNumber(String documentNumber);
    void deleteByDocumentNumber(String documentNumber);
}

package com.example.visitor_ms.port.in.db.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, String> {
    Optional<PersonEntity> findByDocumentNumber(String documentNumber);
}

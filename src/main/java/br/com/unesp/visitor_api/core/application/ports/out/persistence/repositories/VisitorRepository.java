package br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories;

import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findByDocumentNumber(String documentNumber);
}

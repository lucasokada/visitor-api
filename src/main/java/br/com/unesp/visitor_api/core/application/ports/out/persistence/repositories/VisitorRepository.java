package br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories;

import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorEntity, Long> {
    Optional<VisitorEntity> findByDocumentNumber(String documentNumber);
}

package com.example.visitor_ms.adapter.in.person;

import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.repository.VisitorRepository;
import com.example.visitor_ms.port.in.db.visitor.VisitorEntity;
import com.example.visitor_ms.port.in.db.visitor.VisitorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VisitorRepositoryImpl implements VisitorRepository {
    private final VisitorJpaRepository visitorJpaRepository;

    @Override
    public void save(Visitor visitor) {
        VisitorEntity entity = new VisitorEntity(visitor);
        visitorJpaRepository.save(entity);
    }

    @Override
    public Optional<Visitor> findByDocumentNumber(String documentNumber) {
        Optional<VisitorEntity> visitor = visitorJpaRepository.findByDocumentNumber(documentNumber);
        if(visitor.isPresent()) {
            VisitorEntity presentVisitor = visitor.get();
            return Optional.of(presentVisitor.toDomain());
        }

        return Optional.empty();
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return visitorJpaRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public void deleteByDocumentNumber(String documentNumber) {
        visitorJpaRepository.deleteByDocumentNumber(documentNumber);
    }
}

//1 - event source
//2 - resiliencia bd
//3 - persitencia em lote
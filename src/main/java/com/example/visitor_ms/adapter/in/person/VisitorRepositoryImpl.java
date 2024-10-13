package com.example.visitor_ms.adapter.in.person;

import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.repository.VisitorRepository;
import com.example.visitor_ms.port.in.db.repository.visitor.entity.IndividualPersonEntity;
import com.example.visitor_ms.port.in.db.repository.visitor.entity.VisitorEntity;
import com.example.visitor_ms.port.in.db.repository.visitor.repository.VisitorJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class VisitorRepositoryImpl implements VisitorRepository {
    private final VisitorJpaRepository visitorJpaRepository;

    @Override
    public void save(Visitor person) {
        IndividualPersonEntity entity = new IndividualPersonEntity(person);
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
}

//1 - event source
//2 - resiliencia bd
//3 - persitencia em lote
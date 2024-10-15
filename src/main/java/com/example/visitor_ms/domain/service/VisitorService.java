package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.EventType;
import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.command.CreateVisitorCommand;
import com.example.visitor_ms.domain.command.assembler.CreateVisitorCommandAssembler;
import com.example.visitor_ms.domain.dto.EventDto;
import com.example.visitor_ms.domain.dto.VisitorDto;
import com.example.visitor_ms.domain.exception.NotFoundException;
import com.example.visitor_ms.domain.messaging.EventWriter;
import com.example.visitor_ms.domain.repository.VisitorRepository;
import com.example.visitor_ms.domain.usecase.visitor.CreateVisitorUseCase;
import com.example.visitor_ms.domain.usecase.visitor.DeleteVisitorUseCase;
import com.example.visitor_ms.domain.usecase.visitor.GetVisitorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitorService implements CreateVisitorUseCase, GetVisitorUseCase, DeleteVisitorUseCase {
    private final VisitorRepository visitorRepository;
    private final EventWriter eventWriter;

    @Override
    @Transactional
    public Visitor create(CreateVisitorCommand visitorCommand) {
        Optional<Visitor> existentPerson = visitorRepository.findByDocumentNumber(visitorCommand.getDocumentNumber());
        if(existentPerson.isPresent()) {
            return existentPerson.get();
        } else {
            Visitor person = CreateVisitorCommandAssembler.assemble(visitorCommand);
            visitorRepository.save(person);
            eventWriter.publish(new EventDto<>(EventType.CREATE, new VisitorDto(person.getName(),
                    person.getDocumentNumber(), person.getBornAt(), person.getType())));
            return person;
        }
    }

    @Override
    @Transactional
    public Optional<Visitor> getByDocumentNumber(String documentNumber) {
        return visitorRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    @Transactional
    public void deleteByDocumentNumber(String documentNumber) {
        if(!visitorRepository.existsByDocumentNumber(documentNumber)) {
            throw new NotFoundException("Visitor not found with document number " + documentNumber);
        }
        Visitor deletedVisitor = visitorRepository.findByDocumentNumber(documentNumber).orElseThrow();
        visitorRepository.deleteByDocumentNumber(documentNumber);
        eventWriter.publish(new EventDto<>(EventType.DELETE, new VisitorDto(deletedVisitor.getName(), deletedVisitor.getDocumentNumber(),
                deletedVisitor.getBornAt(), deletedVisitor.getType())));
    }
}

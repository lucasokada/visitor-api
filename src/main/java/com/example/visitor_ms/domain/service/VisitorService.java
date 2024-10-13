package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.command.CreateVisitorCommand;
import com.example.visitor_ms.domain.command.assembler.CreateVisitorCommandAssembler;
import com.example.visitor_ms.domain.exception.NotFoundException;
import com.example.visitor_ms.domain.repository.VisitorRepository;
import com.example.visitor_ms.domain.usecase.CreateVisitorUseCase;
import com.example.visitor_ms.domain.usecase.DeleteVisitorUseCase;
import com.example.visitor_ms.domain.usecase.GetVisitorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitorService implements CreateVisitorUseCase, GetVisitorUseCase, DeleteVisitorUseCase {
    private final VisitorRepository visitorRepository;

    @Override
    @Transactional
    public Visitor create(CreateVisitorCommand visitorCommand) {
        Optional<Visitor> existentPerson = visitorRepository.findByDocumentNumber(visitorCommand.getDocumentNumber());
        if(existentPerson.isPresent()) {
            return existentPerson.get();
        } else {
            Visitor person = CreateVisitorCommandAssembler.assemble(visitorCommand);
            visitorRepository.save(person);
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

        visitorRepository.deleteByDocumentNumber(documentNumber);
    }
}

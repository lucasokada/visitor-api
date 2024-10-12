package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.command.CreateVisitorCommand;
import com.example.visitor_ms.domain.repository.VisitorRepository;
import com.example.visitor_ms.domain.usecase.CreateVisitorUseCase;
import com.example.visitor_ms.domain.usecase.GetVisitorUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class VisitorService implements CreateVisitorUseCase, GetVisitorUseCase {
    private final VisitorRepository visitorRepository;

    @Override
    public Visitor create(CreateVisitorCommand visitorCommand) {
        Optional<Visitor> existentPerson = visitorRepository.findByDocumentNumber(visitorCommand.getDocumentNumber());
        if(existentPerson.isPresent()) {
            return existentPerson.get();
        } else {
            Visitor person = new Visitor(visitorCommand.getName(), visitorCommand.getDocumentNumber(), visitorCommand.getBornIn(), visitorCommand.getType());
            visitorRepository.save(person);
            return person;
        }
    }

    @Override
    public Optional<Visitor> getByDocumentNumber(String documentNumber) {
        return visitorRepository.findByDocumentNumber(documentNumber);
    }
}

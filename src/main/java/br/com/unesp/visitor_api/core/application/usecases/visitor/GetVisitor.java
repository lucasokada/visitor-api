package br.com.unesp.visitor_api.core.application.usecases.visitor;

import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.in.GetVisitorUseCase;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories.VisitorRepository;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetVisitor implements GetVisitorUseCase {
    private final VisitorRepository visitorRepository;

    @Override
    public Visitor getByDocumentNumber(String documentNumber) {
        return visitorRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new NotFoundException(String.format("Visitor with document number %s not found", documentNumber)));
    }

    @Override
    public Visitor getById(Long id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Visitor with id %s not found", id)));
    }
}

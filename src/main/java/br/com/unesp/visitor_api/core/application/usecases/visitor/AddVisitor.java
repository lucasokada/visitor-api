package br.com.unesp.visitor_api.core.application.usecases.visitor;

import br.com.unesp.visitor_api.core.application.domain.Visitor;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.DuplicateDocumentException;
import br.com.unesp.visitor_api.core.application.ports.in.AddVisitorUseCase;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import br.com.unesp.visitor_api.core.application.ports.assembler.VisitorAssembler;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.VisitorEntity;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddVisitor implements AddVisitorUseCase {
    private final VisitorRepository visitorRepository;

    @Override
    public Visitor addVisitor(VisitorDTO visitorDTO) {
        verifyExistingVisitor(visitorDTO);
        VisitorEntity entityToSave = VisitorAssembler.dtoToEntity(visitorDTO);
        VisitorEntity savedEntity = visitorRepository.save(entityToSave);
        return VisitorAssembler.entityToDomain(savedEntity);
    }

    private void verifyExistingVisitor(VisitorDTO visitorDTO) {
        Optional<VisitorEntity> existingVisitor = visitorRepository.findByDocumentNumber(visitorDTO.getDocumentNumber());
        if(existingVisitor.isPresent()) {
            throw new DuplicateDocumentException(String.format("Document number %s already exists for person with id %s", existingVisitor.get().getDocumentNumber(), existingVisitor.get().getId()));
        }
    }
}

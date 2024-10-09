package br.com.unesp.visitor_api.unit.usecases.visitor;

import br.com.unesp.visitor_api.core.application.domain.Visitor;
import br.com.unesp.visitor_api.core.application.domain.exceptions.DuplicateDocumentException;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.VisitorEntity;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories.VisitorRepository;
import br.com.unesp.visitor_api.core.application.usecases.visitor.AddVisitor;
import br.com.unesp.visitor_api.mocks.dto.VisitorDTOMock;
import br.com.unesp.visitor_api.mocks.entities.VisitorEntityMock;
import br.com.unesp.visitor_api.mocks.model.VisitorMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AddVisitorTest {
    @InjectMocks
    private AddVisitor addVisitor;

    @Mock
    private VisitorRepository visitorRepository;

    @Test
    void addVisitor_whenNotDuplicatedDocumentNumber_expectCallSave() {
        VisitorDTO request = VisitorDTOMock.mock();
        Visitor expected = VisitorMock.mock();

        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());
        when(visitorRepository.save(any(VisitorEntity.class))).thenReturn(VisitorEntityMock.mockWithId());

        var response = addVisitor.addVisitor(request);

        assertEquals(expected, response);
        verify(visitorRepository).findByDocumentNumber(request.getDocumentNumber());
        verify(visitorRepository).save(VisitorEntityMock.mockWithoutId());
    }

    @Test
    void addVisitor_whenDuplicatedDocumentNumber_expectThrowDuplicateDocumentException() {
        VisitorDTO request = VisitorDTOMock.mock();

        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(VisitorEntityMock.mockWithId()));
        when(visitorRepository.save(any(VisitorEntity.class))).thenReturn(VisitorEntityMock.mockWithoutId());

        assertThrows(DuplicateDocumentException.class, () -> addVisitor.addVisitor(request));

        verify(visitorRepository).findByDocumentNumber(request.getDocumentNumber());
        verify(visitorRepository, never()).save(any(VisitorEntity.class));
    }

    @Test
    void addVisitor_whenSaveException_expectThrowException() {
        VisitorDTO request = VisitorDTOMock.mock();

        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());
        when(visitorRepository.save(any(VisitorEntity.class))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> addVisitor.addVisitor(request));

        verify(visitorRepository).findByDocumentNumber(request.getDocumentNumber());
        verify(visitorRepository).save(VisitorEntityMock.mockWithoutId());
    }
}

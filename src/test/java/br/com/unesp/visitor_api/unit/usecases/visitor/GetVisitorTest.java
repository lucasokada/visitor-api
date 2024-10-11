package br.com.unesp.visitor_api.unit.usecases.visitor;

import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories.VisitorRepository;
import br.com.unesp.visitor_api.core.application.usecases.visitor.GetVisitor;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.NotFoundException;
import br.com.unesp.visitor_api.mocks.entities.VisitorMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GetVisitorTest {
    @InjectMocks
    private GetVisitor getVisitor;

    @Mock
    VisitorRepository visitorRepository;

    @Test
    void getByDocumentNumber_whenSuccess_expectReturnVisitor() {
        String documentNumber = "37055587069";
        Optional<Visitor> visitor = Optional.of(VisitorMock.mockWithId());

        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(visitor);

        var result = getVisitor.getByDocumentNumber(documentNumber);

        assertEquals(visitor.get(), result);
        verify(visitorRepository).findByDocumentNumber(documentNumber);
    }

    @Test
    void getByDocumentNumber_whenNotFound_expectThrowNotFoundException() {
        String documentNumber = "37055587069";
        Optional<Visitor> visitor = Optional.empty();

        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(visitor);

        assertThrows(NotFoundException.class, () -> getVisitor.getByDocumentNumber(documentNumber));

        verify(visitorRepository).findByDocumentNumber(documentNumber);
    }

    @Test
    void getById_whenSuccess_expectReturnVisitor() {
        Long id = 1L;
        Optional<Visitor> visitor = Optional.of(VisitorMock.mockWithId());

        when(visitorRepository.findById(anyLong())).thenReturn(visitor);

        var result = getVisitor.getById(id);

        assertEquals(visitor.get(), result);
        verify(visitorRepository).findById(1L);
    }

    @Test
    void getById_whenNotFound_expectThrowNotFoundException() {
        Long id = 1L;
        Optional<Visitor> visitor = Optional.empty();

        when(visitorRepository.findById(anyLong())).thenReturn(visitor);

        assertThrows(NotFoundException.class, () -> getVisitor.getById(id));

        verify(visitorRepository).findById(1L);
    }
}

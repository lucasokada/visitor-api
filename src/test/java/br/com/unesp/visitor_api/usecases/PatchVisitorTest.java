package br.com.unesp.visitor_api.usecases;

import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchVisitorDTO;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories.VisitorRepository;
import br.com.unesp.visitor_api.core.application.usecases.visitor.PatchVisitor;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.NotFoundException;
import br.com.unesp.visitor_api.mocks.dto.patch.PatchVisitorDTOMock;
import br.com.unesp.visitor_api.mocks.entities.VisitorMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatchVisitorTest {
    @Autowired
    private PatchVisitor patchVisitor;

    @Autowired
    private VisitorRepository visitorRepository;

    @Test
    @Transactional
    void patchVisitor_whenCompleteEntityChange_expectUpdateEntityFields() {
        Long visitorId = 1L;
        PatchVisitorDTO request = PatchVisitorDTOMock.mock();
        Visitor expected = VisitorMock.mockEdited();

        visitorRepository.save(VisitorMock.mockWithoutId());

        patchVisitor.patchVisitor(request, visitorId);

        Visitor edited = visitorRepository.findById(visitorId).orElseThrow();
        assertEquals(expected, edited);
    }

    @Test
    @Transactional
    void patchVisitor_whenPartialEntityChange_expectUpdateEntityFields() {
        Long visitorId = 1L;
        PatchVisitorDTO request = PatchVisitorDTOMock.mockPartialEdited();
        Visitor expected = VisitorMock.mockPartialEdited();

        visitorRepository.save(VisitorMock.mockWithoutId());

        patchVisitor.patchVisitor(request, visitorId);

        Visitor edited = visitorRepository.findById(visitorId).orElseThrow();
        assertEquals(expected, edited);
    }

    @Test
    @Transactional
    void patchVisitor_whenAddressWithoutId_expectInsertNewAddress() {
        Long visitorId = 1L;
        PatchVisitorDTO request = PatchVisitorDTOMock.mockWithNewAddress();
        Visitor expected = VisitorMock.mockEditedWithNewAddress();

        visitorRepository.save(VisitorMock.mockWithoutId());

        patchVisitor.patchVisitor(request, visitorId);

        Visitor edited = visitorRepository.findById(visitorId).orElseThrow();
        assertEquals(expected, edited);
    }

    @Test
    void patchVisitor_whenVisitorIdNotExists_expectNotFoundException() {
        Long visitorId = 1L;
        PatchVisitorDTO request = PatchVisitorDTOMock.mock();

        assertThrows(NotFoundException.class, () -> patchVisitor.patchVisitor(request, visitorId));

        assertTrue(visitorRepository.findById(visitorId).isEmpty());
    }
}

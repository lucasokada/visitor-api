package br.com.unesp.visitor_api.usecases;

import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories.VisitorRepository;
import br.com.unesp.visitor_api.core.application.usecases.visitor.GetVisitor;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.NotFoundException;
import br.com.unesp.visitor_api.mocks.entities.VisitorMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GetVisitorTest {
    @Autowired
    private GetVisitor getVisitor;

    @Autowired
    private VisitorRepository visitorRepository;

    @Test
    @Transactional
    void getByDocumentNumber_whenSuccess_expectReturnVisitor() {
        String documentNumber = "37055587069";
        Visitor visitor = VisitorMock.mockWithId();

        visitorRepository.save(VisitorMock.mockWithoutId());
        var result = getVisitor.getByDocumentNumber(documentNumber);

        assertEquals(visitor, result);
    }

    @Test
    void getByDocumentNumber_whenNotFound_expectThrowNotFoundException() {
        String documentNumber = "37055587069";

        assertThrows(NotFoundException.class, () -> getVisitor.getByDocumentNumber(documentNumber));

    }

    @Test
    @Transactional
    void getById_whenSuccess_expectReturnVisitor() {
        Long id = 1L;

        Visitor newVisitor = visitorRepository.save(VisitorMock.mockWithoutId());
        var result = getVisitor.getById(id);

        assertEquals(newVisitor, result);
    }

    @Test
    void getById_whenNotFound_expectThrowNotFoundException() {
        Long id = 1L;

        assertThrows(NotFoundException.class, () -> getVisitor.getById(id));
    }
}

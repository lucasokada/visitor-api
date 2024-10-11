package br.com.unesp.visitor_api.usecases;

import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import br.com.unesp.visitor_api.core.application.usecases.visitor.AddVisitor;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.DuplicateDocumentException;
import br.com.unesp.visitor_api.mocks.dto.VisitorDTOMock;
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
class AddVisitorTest {
    @Autowired
    private AddVisitor addVisitor;

    @Test
    @Transactional
    void addVisitor_whenNotDuplicatedDocumentNumber_expectCallSave() {
        VisitorDTO request = VisitorDTOMock.mock();
        Visitor expected = VisitorMock.mockWithId();

        var response = addVisitor.addVisitor(request);

        assertEquals(expected, response);
    }

    @Test
    @Transactional
    void addVisitor_whenDuplicatedDocumentNumber_expectThrowDuplicateDocumentException() {
        VisitorDTO request = VisitorDTOMock.mock();

        addVisitor.addVisitor(request);
        assertThrows(DuplicateDocumentException.class, () -> addVisitor.addVisitor(request));
    }
}

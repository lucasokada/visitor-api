package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.Contact;
import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.VisitorType;
import com.example.visitor_ms.domain.command.CreateContactCommand;
import com.example.visitor_ms.domain.command.CreateVisitorCommand;
import com.example.visitor_ms.domain.repository.VisitorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class VisitorServiceTest {
    @InjectMocks
    private VisitorService visitorService;

    @Mock
    private VisitorRepository visitorRepository;

    private CreateVisitorCommand validVisitorCommand() {
        return new CreateVisitorCommand("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContactCommand());
    }

    private CreateContactCommand validContactCommand() {
        return new CreateContactCommand("6930138401", "9626759827", "1935794707", "name@email.com");
    }

    private Visitor validVisitor() {
        return new Visitor("Augusto João da Rosa", "11309929939", LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact());
    }

    private Contact validContact() {
        return new Contact("6930138401", "9626759827", "1935794707", "name@email.com");
    }

    @Test
    void create_whenSuccess_expectReturnPerson() {
        CreateVisitorCommand request = validVisitorCommand();
        Visitor visitor = validVisitor();
        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());

        Visitor result = visitorService.create(request);

        assertEquals(visitor, result);
        verify(visitorRepository).save(visitor);
    }

    @Test
    void create_whenExistentPerson_expectReturnPerson() {
        CreateVisitorCommand request = validVisitorCommand();
        Visitor visitor = new Visitor("Camila Eliane Marina Farias", "11309929939", LocalDate.of(1960, 3, 5), VisitorType.RELATED, validContact());
        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(visitor));

        Visitor result = visitorService.create(request);

        assertEquals(visitor, result);
        verify(visitorRepository, never()).save(any(Visitor.class));
    }

    @Test
    void create_whenSaveThrowException_expectThrowException() {
        CreateVisitorCommand request = validVisitorCommand();
        Visitor visitor = validVisitor();
        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());
        doThrow(RuntimeException.class).when(visitorRepository).save(any(Visitor.class));

        assertThrows(RuntimeException.class, () -> visitorService.create(request));

        verify(visitorRepository).save(visitor);
    }

    @Test
    void getByDocumentNumber_whenFoundVisitor_expectReturnVisitor() {
        String documentNumber = "11309929939";
        Visitor visitor = validVisitor();

        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(visitor));

        Optional<Visitor> result = visitorService.getByDocumentNumber(documentNumber);

        assertEquals(Optional.of(visitor), result);
    }

    @Test
    void getByDocumentNumber_whenNotFoundVisitor_expectReturnOptionalEmpty() {
        String documentNumber = "11309929939";

        when(visitorRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());

        Optional<Visitor> result = visitorService.getByDocumentNumber(documentNumber);

        assertEquals(Optional.empty(), result);
    }
}

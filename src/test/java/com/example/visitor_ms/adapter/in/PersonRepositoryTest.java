package com.example.visitor_ms.adapter.in;

import com.example.visitor_ms.adapter.in.person.VisitorRepositoryImpl;
import com.example.visitor_ms.domain.Access;
import com.example.visitor_ms.domain.Address;
import com.example.visitor_ms.domain.BrazilState;
import com.example.visitor_ms.domain.Contact;
import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.VisitorType;
import com.example.visitor_ms.port.in.db.repository.visitor.entity.VisitorEntity;
import com.example.visitor_ms.port.in.db.repository.visitor.repository.VisitorJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PersonRepositoryTest {
    @Autowired
    private VisitorRepositoryImpl visitorRepository;

    @Autowired
    private VisitorJpaRepository visitorJpaRepository;

    private Contact validContact = new Contact("6930138401", "9626759827",
            "1935794707", "name@email.com");
    private Access validAccess = new Access("userName", "PassworD123!");
    private Set<Address> validAddresses = new HashSet<>() {{
        add(new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)", "13506517", "Rio Claro", BrazilState.SP));
        add(new Address("Avenida 7", 773, "Jardim Claret", "13503255", "Rio Claro", BrazilState.SP));
    }};
    private Visitor validVisitor= new Visitor("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact, validAccess,
                validAddresses);

    @Transactional
    @Test
    void save_whenSuccess_expectSavePerson() {
        visitorRepository.save(validVisitor);

        VisitorEntity persisted = visitorJpaRepository.findByDocumentNumber(validVisitor.getDocumentNumber()).orElseThrow();

        assertEquals(validVisitor, persisted.toDomain());
    }

    @Transactional
    @Test
    void save_whenDocumentAlreadyExists_expectReturnExistentVisitor() {
        Visitor existent = new Visitor("Laura Sophie Rafaela Silva", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact, validAccess,
                validAddresses);
        visitorJpaRepository.save(new VisitorEntity(existent));

        visitorRepository.save(validVisitor);

        VisitorEntity persisted = visitorJpaRepository.findByDocumentNumber(validVisitor.getDocumentNumber()).orElseThrow();

        assertEquals(existent, persisted.toDomain());
    }

    @Test
    @Transactional
    void findByDocumentNumber_whenExistentDocumentNumber_expectReturnOptionalOfVisitor() {
        visitorJpaRepository.save(new VisitorEntity(validVisitor));

        Optional<Visitor> existentVisitor = visitorRepository.findByDocumentNumber(validVisitor.getDocumentNumber());

        assertEquals(validVisitor, existentVisitor.get());
    }

    @Test
    @Transactional
    void findByDocumentNumber_whenNotExistentDocumentNumber_expectReturnOptionalOfEmpty() {
        String documentNumber = "81142337937";
        visitorJpaRepository.save(new VisitorEntity(validVisitor));

        Optional<Visitor> existentVisitor = visitorRepository.findByDocumentNumber(documentNumber);

        assertEquals(Optional.empty(), existentVisitor);
    }
}

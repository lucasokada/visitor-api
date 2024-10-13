package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidCompanyException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompanyTest {
    private Contact validContact = new Contact("6930138401", "9626759827", "1935794707", "name@email.com");
    private Access validAccess = new Access("userName", "PassworD123!");
    private Set<Address> validAddresses = new HashSet<>() {{
        add(new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)", "13506517", "Rio Claro", BrazilState.SP));
        add(new Address("Avenida 7", 773, "Jardim Claret", "13503255", "Rio Claro", BrazilState.SP));
    }};
    private Set<Visitor> validVisitors = new HashSet<>() {{
        add(new Visitor("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.SERVICE_PROVIDER, validContact, validAccess,
                validAddresses));
        add(new Visitor("Cláudia Maya Melo", "57356149915",
                LocalDate.of(1996, 5, 27), VisitorType.SERVICE_PROVIDER, validContact, validAccess,
                validAddresses));
    }};
    private Set<Visitor> relatedVisitors = new HashSet<>() {{
        add(new Visitor("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact, validAccess,
                validAddresses));
        add(new Visitor("Cláudia Maya Melo", "57356149915",
                LocalDate.of(1996, 5, 27), VisitorType.RELATED, validContact, validAccess,
                validAddresses));
    }};
    private Set<Visitor> otherVisitors = new HashSet<>() {{
        add(new Visitor("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.OTHER, validContact, validAccess,
                validAddresses));
        add(new Visitor("Cláudia Maya Melo", "57356149915",
                LocalDate.of(1996, 5, 27), VisitorType.OTHER, validContact, validAccess,
                validAddresses));
    }};

    @Test
    void company_whenValidCompany_expectCreateNewCompany() {
        assertDoesNotThrow(() -> new Company("Universidade Estadual Paulista \"Júlio de Mesquita Filho\"",
                "48031918001872", validVisitors));
    }

    @Test
    void company_whenCompanyWithRelatedVisitorType_expectThrowInvalidCompanyException() {
        assertThrows(InvalidCompanyException.class, () -> new Company("Universidade Estadual Paulista \"Júlio de Mesquita Filho\"",
                "48031918001872", relatedVisitors));
    }

    @Test
    void company_whenCompanyWithOtherVisitorType_expectThrowInvalidCompanyException() {
        assertThrows(InvalidCompanyException.class, () -> new Company("Universidade Estadual Paulista \"Júlio de Mesquita Filho\"",
                "48031918001872", otherVisitors));
    }
}

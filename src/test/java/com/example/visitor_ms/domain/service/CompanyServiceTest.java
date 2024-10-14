package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.Access;
import com.example.visitor_ms.domain.Address;
import com.example.visitor_ms.domain.BrazilState;
import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.Contact;
import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.VisitorType;
import com.example.visitor_ms.domain.command.CreateCompanyCommand;
import com.example.visitor_ms.domain.exception.InvalidCompanyVisitorTypeException;
import com.example.visitor_ms.domain.exception.NotFoundException;
import com.example.visitor_ms.domain.repository.CompanyRepository;
import com.example.visitor_ms.domain.repository.VisitorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private VisitorRepository visitorRepository;

    @Test
    void create_whenSuccess_expectReturnCompany() {
        Company company = new Company("Condomínio Swift", "37992724000179");
        CreateCompanyCommand request = new CreateCompanyCommand("Condomínio Swift", "37992724000179");

        when(companyRepository.findByDocumentNumber(anyString())).thenReturn(Optional.empty());

        Company result = companyService.create(request);

        assertEquals(company, result);
        verify(companyRepository).findByDocumentNumber(request.getDocumentNumber());
        verify(companyRepository).save(company);
    }

    @Test
    void create_whenDocumentNumberAlreadyExists_expectReturnExistentCompanyAndNotCallSave() {
        Company existentCompany = new Company("Condimínio Existente", "37992724000179");
        CreateCompanyCommand request = new CreateCompanyCommand("Condomínio Swift", "37992724000179");

        when(companyRepository.findByDocumentNumber(anyString())).thenReturn(Optional.of(existentCompany));

        Company result = companyService.create(request);

        assertEquals(existentCompany, result);
        verify(companyRepository).findByDocumentNumber(request.getDocumentNumber());
        verify(companyRepository, never()).save(any(Company.class));
    }

    @Test
    void associate_whenSuccess_expectSaveAssociatedServiceProviders() {
        Company existentCompany = new Company("Empresa Existente", "37992724000179");
        Set<String> serviceProvidersDocumentNumbers = Set.of("11309929939", "05788203821");

        Contact validContact = new Contact("6930138401", "9626759827",
                "1935794707", "name@email.com");
        Access validAccess = new Access("userName", "PassworD123!");
        Access validAccess2 = new Access("userName2", "PassworD123!");
        Set<Address> validAddresses = new HashSet<>() {{
            add(new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)", "13506517", "Rio Claro", BrazilState.SP));
            add(new Address("Avenida 7", 773, "Jardim Claret", "13503255", "Rio Claro", BrazilState.SP));
        }};
        Visitor validVisitor= new Visitor("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.SERVICE_PROVIDER, validContact, validAccess,
                validAddresses);
        Visitor validVisitor2= new Visitor("Allana Ana Elaine Rodrigues", "05788203821",
                LocalDate.of(1949, 10, 3), VisitorType.SERVICE_PROVIDER, validContact, validAccess2,
                validAddresses);

        when(companyRepository.findByDocumentNumber("37992724000179")).thenReturn(Optional.of(existentCompany));
        when(visitorRepository.findAllById(serviceProvidersDocumentNumbers)).thenReturn(Set.of(validVisitor, validVisitor2));

        companyService.associate("37992724000179", new HashSet<>(serviceProvidersDocumentNumbers));

        existentCompany.addAllServiceProviders(Set.of(validVisitor, validVisitor2));
        verify(companyRepository).save(existentCompany);
    }

    @Test
    void associate_whenCompanyNumberNotExists_expectThrowNotFoundException() {
        Company existentCompany = new Company("Empresa Existente", "37992724000179");
        Set<String> serviceProvidersDocumentNumbers = Set.of("11309929939", "05788203821");

        when(companyRepository.findByDocumentNumber("37992724000179")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> companyService.associate("37992724000179",
                new HashSet<>(serviceProvidersDocumentNumbers)));

        verify(visitorRepository, never()).findAllById(anyList());
        verify(companyRepository, never()).save(any());
    }

    @Test
    void associate_whenServiceProviderNotFound_expectThrowNotFoundException() {
        Company existentCompany = new Company("Empresa Existente", "37992724000179");
        Set<String> serviceProvidersDocumentNumbers = Set.of("11309929939", "05788203822");

        Contact validContact = new Contact("6930138401", "9626759827",
                "1935794707", "name@email.com");
        Access validAccess = new Access("userName", "PassworD123!");
        Set<Address> validAddresses = new HashSet<>() {{
            add(new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)", "13506517", "Rio Claro", BrazilState.SP));
            add(new Address("Avenida 7", 773, "Jardim Claret", "13503255", "Rio Claro", BrazilState.SP));
        }};
        Visitor validVisitor= new Visitor("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.SERVICE_PROVIDER, validContact, validAccess,
                validAddresses);

        when(companyRepository.findByDocumentNumber("37992724000179")).thenReturn(Optional.of(existentCompany));
        when(visitorRepository.findAllById(serviceProvidersDocumentNumbers)).thenReturn(Set.of(validVisitor));

        assertThrows(NotFoundException.class, () -> companyService.associate("37992724000179",
                new HashSet<>(serviceProvidersDocumentNumbers)));

        verify(companyRepository, never()).save(any());
    }

    @Test
    void associate_whenVisitorType_expectInvalidCompanyVisitorTypeException() {
        Company existentCompany = new Company("Empresa Existente", "37992724000179");
        Set<String> serviceProvidersDocumentNumbers = Set.of("11309929939", "05788203821");

        Contact validContact = new Contact("6930138401", "9626759827",
                "1935794707", "name@email.com");
        Access validAccess = new Access("userName", "PassworD123!");
        Access validAccess2 = new Access("userName2", "PassworD123!");
        Set<Address> validAddresses = new HashSet<>() {{
            add(new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)", "13506517", "Rio Claro", BrazilState.SP));
            add(new Address("Avenida 7", 773, "Jardim Claret", "13503255", "Rio Claro", BrazilState.SP));
        }};
        Visitor validVisitor= new Visitor("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.SERVICE_PROVIDER, validContact, validAccess,
                validAddresses);
        Visitor validVisitor2= new Visitor("Allana Ana Elaine Rodrigues", "05788203821",
                LocalDate.of(1949, 10, 3), VisitorType.RELATED, validContact, validAccess2,
                validAddresses);

        when(companyRepository.findByDocumentNumber("37992724000179")).thenReturn(Optional.of(existentCompany));
        when(visitorRepository.findAllById(serviceProvidersDocumentNumbers)).thenReturn(Set.of(validVisitor, validVisitor2));

        assertThrows(InvalidCompanyVisitorTypeException.class, () -> companyService.associate("37992724000179", new HashSet<>(serviceProvidersDocumentNumbers)));

        verify(companyRepository, never()).save(any());
    }
}

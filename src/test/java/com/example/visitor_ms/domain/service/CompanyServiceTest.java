package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.command.CreateCompanyCommand;
import com.example.visitor_ms.domain.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
}

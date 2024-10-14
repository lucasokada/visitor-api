package com.example.visitor_ms.adapter.in.company;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.port.in.db.company.CompanyEntity;
import com.example.visitor_ms.port.in.db.company.CompanyJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CompanyRepositoryImplTest {
    @Autowired
    private CompanyRepositoryImpl companyRepository;

    @Autowired
    private CompanyJpaRepository companyJpaRepository;

    private Company validCompany = new Company("Condomínio Swift", "37992724000179");

    @Transactional
    @Test
    void save_whenSuccess_expectSaveCompany() {
        companyRepository.save(validCompany);

        CompanyEntity persisted = companyJpaRepository.findByDocumentNumber(validCompany.getDocumentNumber()).orElseThrow();

        assertEquals(validCompany, persisted.toDomain());
    }

    @Transactional
    @Test
    void save_whenDocumentAlreadyExists_expectUpdateExistentCompany() {
        Company existent = new Company("Condomínio Existente", "37992724000179");

        companyRepository.save(existent);
        companyRepository.save(validCompany);

        CompanyEntity persisted = companyJpaRepository.findByDocumentNumber("37992724000179").orElseThrow();

        assertEquals(validCompany, persisted.toDomain());
    }

    @Test
    @Transactional
    void findByDocumentNumber_whenExistentDocumentNumber_expectReturnOptionalOfVisitor() {
        companyJpaRepository.save(new CompanyEntity(validCompany));

        Optional<Company> existentVisitor = companyRepository.findByDocumentNumber(validCompany.getDocumentNumber());

        assertEquals(validCompany, existentVisitor.get());
    }

    @Test
    @Transactional
    void findByDocumentNumber_whenNotExistentDocumentNumber_expectReturnOptionalOfEmpty() {
        String documentNumber = "37992724000170";
        companyJpaRepository.save(new CompanyEntity(validCompany));

        Optional<Company> existentVisitor = companyRepository.findByDocumentNumber(documentNumber);

        assertEquals(Optional.empty(), existentVisitor);
    }
}

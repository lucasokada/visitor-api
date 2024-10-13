package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.command.CreateCompanyCommand;
import com.example.visitor_ms.domain.command.assembler.CreateCompanyCommandAssembler;
import com.example.visitor_ms.domain.repository.CompanyRepository;
import com.example.visitor_ms.domain.usecase.company.CreateCompanyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService implements CreateCompanyUseCase {
    private CompanyRepository companyRepository;

    @Override
    public Company create(CreateCompanyCommand createCompanyCommand) {
        Optional<Company> existentCompany = companyRepository.findByDocumentNumber(createCompanyCommand.getDocumentNumber());
        if(existentCompany.isPresent()) {
            return existentCompany.get();
        } else {
            Company company = CreateCompanyCommandAssembler.assemble(createCompanyCommand);
            companyRepository.save(company);
            return company;
        }
    }
}

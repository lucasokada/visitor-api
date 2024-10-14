package com.example.visitor_ms.domain.service;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.command.CreateCompanyCommand;
import com.example.visitor_ms.domain.command.assembler.CreateCompanyCommandAssembler;
import com.example.visitor_ms.domain.exception.NotFoundException;
import com.example.visitor_ms.domain.repository.CompanyRepository;
import com.example.visitor_ms.domain.repository.VisitorRepository;
import com.example.visitor_ms.domain.usecase.company.AssociateVisitorsUseCase;
import com.example.visitor_ms.domain.usecase.company.CreateCompanyUseCase;
import com.example.visitor_ms.domain.usecase.company.GetCompanyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService implements CreateCompanyUseCase, GetCompanyUseCase, AssociateVisitorsUseCase {
    private final CompanyRepository companyRepository;
    private final VisitorRepository visitorRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void associate(String companyDocumentNumber, Set<String> serviceProvidersDocumentNumbers) {
        Company company = companyRepository.findByDocumentNumber(companyDocumentNumber)
                .orElseThrow(() -> new NotFoundException("company not found for cnpj " + companyDocumentNumber));
        Set<Visitor> existentServiceProviders = visitorRepository.findAllById(serviceProvidersDocumentNumbers);
        validateAllVisitorsFoundByDocumentNumbers(existentServiceProviders, serviceProvidersDocumentNumbers);
        company.addAllServiceProviders(existentServiceProviders);
        companyRepository.save(company);
    }

    private void validateAllVisitorsFoundByDocumentNumbers(Set<Visitor> foundVisitors, Set<String> documentNumbers) {
        if(foundVisitors.size() == documentNumbers.size()) {
            return;
        }
        Set<String> foundVisitorsDocumentNumbers = foundVisitors
                .stream()
                .map(Visitor::getDocumentNumber)
                .collect(Collectors.toSet());
        Set<String> notFoundDocumentNumbers = new HashSet<>(documentNumbers);
        notFoundDocumentNumbers.removeAll(foundVisitorsDocumentNumbers);
        throw new NotFoundException("visitors not found for document numbers " + notFoundDocumentNumbers);
    }

    @Override
    @Transactional
    public Optional<Company> getByDocumentNumber(String documentNumber) {
        return companyRepository.findByDocumentNumber(documentNumber);
    }
}

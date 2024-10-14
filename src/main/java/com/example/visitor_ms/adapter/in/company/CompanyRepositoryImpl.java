package com.example.visitor_ms.adapter.in.company;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.repository.CompanyRepository;
import com.example.visitor_ms.port.in.db.company.CompanyEntity;
import com.example.visitor_ms.port.in.db.company.CompanyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {
    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public void save(Company company) {
        CompanyEntity entity = new CompanyEntity(company);
        companyJpaRepository.save(entity);
    }

    @Override
    public Optional<Company> findByDocumentNumber(String documentNumber) {
        Optional<CompanyEntity> company = companyJpaRepository.findById(documentNumber);
        if(company.isPresent()) {
            CompanyEntity presentCompany = company.get();
            return Optional.of(presentCompany.toDomain());
        }

        return Optional.empty();
    }
}

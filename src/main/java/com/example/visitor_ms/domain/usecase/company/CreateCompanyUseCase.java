package com.example.visitor_ms.domain.usecase.company;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.command.CreateCompanyCommand;

public interface CreateCompanyUseCase {
    Company create(CreateCompanyCommand createCompanyCommand);
}

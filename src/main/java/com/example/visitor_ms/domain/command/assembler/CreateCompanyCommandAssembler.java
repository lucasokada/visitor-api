package com.example.visitor_ms.domain.command.assembler;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.command.CreateCompanyCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCompanyCommandAssembler {
    public static Company assemble(CreateCompanyCommand createCompanyCommand) {
        return new Company(createCompanyCommand.getName(), createCompanyCommand.getDocumentNumber());
    }
}

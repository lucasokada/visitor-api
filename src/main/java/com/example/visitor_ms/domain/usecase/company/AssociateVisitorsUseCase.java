package com.example.visitor_ms.domain.usecase.company;

import java.util.Set;

public interface AssociateVisitorsUseCase {
    void associate(String companyDocumentNumber, Set<String> serviceProvidersDocumentNumbers);
}

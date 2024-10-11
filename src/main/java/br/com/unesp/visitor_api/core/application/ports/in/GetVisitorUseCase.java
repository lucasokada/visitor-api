package br.com.unesp.visitor_api.core.application.ports.in;

import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;

public interface GetVisitorUseCase {
    Visitor getByDocumentNumber(String documentNumber);
    Visitor getById(Long id);
}

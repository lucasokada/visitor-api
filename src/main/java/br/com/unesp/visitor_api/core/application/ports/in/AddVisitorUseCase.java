package br.com.unesp.visitor_api.core.application.ports.in;

import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;

public interface AddVisitorUseCase {
    Visitor addVisitor(VisitorDTO visitorDTO);
}

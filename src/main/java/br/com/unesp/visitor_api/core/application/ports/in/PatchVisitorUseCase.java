package br.com.unesp.visitor_api.core.application.ports.in;

import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchVisitorDTO;

public interface PatchVisitorUseCase {
    void patchVisitor(PatchVisitorDTO patchVisitorDTO, Long id);
}

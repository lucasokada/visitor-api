package br.com.unesp.visitor_api.mocks.dto.patch;

import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchContactDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchContactDTOMock {
    public static PatchContactDTO mock() {
        return new PatchContactDTO("1733355245", "1932564345", "19984487897", "editedVisitor1@email.com");
    }

    public static PatchContactDTO mockPartialEdited() {
        return new PatchContactDTO("1933616242", null, "19984487897", "editedVisitor1@email.com");
    }
}

package br.com.unesp.visitor_api.mocks.dto.patch;

import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchAccessDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchAccessDTOMock {
    public static PatchAccessDTO mock() {
        return new PatchAccessDTO("editedVisitor1", "editedPassVisitor1");
    }

    public static PatchAccessDTO mockPartialEdited() {
        return new PatchAccessDTO(null, "editedPassVisitor1");
    }
}

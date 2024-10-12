package br.com.unesp.visitor_api.mocks.dto.patch;

import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchVisitorDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchVisitorDTOMock {
    public static PatchVisitorDTO mock() {
        return new PatchVisitorDTO("visitor 1 edit", LocalDate.of(2024, 10, 11), "42540120040",
                PatchContactDTOMock.mock(), PatchAccessDTOMock.mock(), PatchAddressDTOMock.mockList());
    }

    public static PatchVisitorDTO mockWithNewAddress() {
        return new PatchVisitorDTO("visitor 1 edit", LocalDate.of(2024, 10, 11), "42540120040",
                PatchContactDTOMock.mock(), PatchAccessDTOMock.mock(), PatchAddressDTOMock.mockListWithNewAddress());
    }

    public static PatchVisitorDTO mockPartialEdited() {
        return new PatchVisitorDTO("visitor", LocalDate.of(2024, 10, 11), "37055587069",
                PatchContactDTOMock.mockPartialEdited(), PatchAccessDTOMock.mockPartialEdited(), PatchAddressDTOMock.mockListPartialEdit());
    }
}

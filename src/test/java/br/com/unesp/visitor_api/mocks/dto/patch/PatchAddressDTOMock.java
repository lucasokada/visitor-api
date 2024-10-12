package br.com.unesp.visitor_api.mocks.dto.patch;

import br.com.unesp.visitor_api.core.application.domain.entities.enums.BrazilState;
import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchAddressDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchAddressDTOMock {
    public static List<PatchAddressDTO> mockList() {
        return List.of(
                new PatchAddressDTO(1L, "Rua 2 RV", 484, "Conjunto Residencial Vila Verde", "13506133", "Rio Claro", BrazilState.SP),
                new PatchAddressDTO(2L, "Rodovia Washington Luiz", 662, "Jardim Rio Claro", "13503750", "Rio Claro", BrazilState.SP)
        );
    }

    public static List<PatchAddressDTO> mockListWithNewAddress() {
        return List.of(
                new PatchAddressDTO(1L, "Rua 2 RV", 484, "Conjunto Residencial Vila Verde", "13506133", "Rio Claro", BrazilState.SP),
                new PatchAddressDTO(2L, "Rodovia Washington Luiz", 662, "Jardim Rio Claro", "13503750", "Rio Claro", BrazilState.SP),
                new PatchAddressDTO(null, "Avenida 52 Particular", 37, "Jardim Portugal", "13504075", "Rio Claro", BrazilState.SP)

        );
    }

    public static List<PatchAddressDTO> mockListPartialEdit() {
        return List.of(
                new PatchAddressDTO(1L, null, 155, "Jardim Itapuã", "13501618", "Rio Claro", BrazilState.SP),
                new PatchAddressDTO(2L, "Rodovia Washington Luiz", 662, "Jardim Rio Claro", "13503750", null, null)
        );
    }
}

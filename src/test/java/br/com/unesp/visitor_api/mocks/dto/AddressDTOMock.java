package br.com.unesp.visitor_api.mocks.dto;

import br.com.unesp.visitor_api.core.application.domain.model.enums.BrazilState;
import br.com.unesp.visitor_api.core.application.ports.dto.AddressDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressDTOMock {
    public static List<AddressDTO> mockList() {
        return List.of(
                new AddressDTO("Avenida 65", 155, "Jardim Itapuã", "13501618", "Rio Claro", BrazilState.SP),
                new AddressDTO("Avenida 36", 65, "Jardim Bela Vista", "13504266", "Rio Claro", BrazilState.SP)
        );
    }
}

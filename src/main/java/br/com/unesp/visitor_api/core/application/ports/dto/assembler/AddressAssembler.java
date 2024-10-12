package br.com.unesp.visitor_api.core.application.ports.dto.assembler;

import br.com.unesp.visitor_api.core.application.domain.entities.Address;
import br.com.unesp.visitor_api.core.application.ports.dto.AddressDTO;
import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchAddressDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressAssembler {
    public static Address dtoToEntity(AddressDTO dto) {
        return new Address(null, dto.getStreet(), dto.getNumber(), dto.getNeighborhood(), dto.getZipCode(),
                dto.getCity(), dto.getState(), null);
    }

    public static Address dtoToEntity(PatchAddressDTO dto) {
        return new Address(null, dto.getStreet(), dto.getNumber(), dto.getNeighborhood(), dto.getZipCode(),
                dto.getCity(), dto.getState(), null);
    }
}

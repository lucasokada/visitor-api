package br.com.unesp.visitor_api.core.application.ports.assembler;

import br.com.unesp.visitor_api.core.application.domain.Address;
import br.com.unesp.visitor_api.core.application.ports.dto.AddressDTO;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.AddressEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressAssembler {
    public static AddressEntity dtoToEntity(AddressDTO dto) {
        return new AddressEntity(null, dto.getStreet(), dto.getNumber(), dto.getNeighborhood(), dto.getZipCode(),
                dto.getCity(), dto.getState(), null);
    }

    public static Address entityToDomain(AddressEntity entity) {
        return new Address(entity.getStreet(), entity.getNumber(), entity.getNeighborhood(), entity.getZipCode(), entity.getCity(), entity.getState());
    }
}

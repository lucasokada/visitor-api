package br.com.unesp.visitor_api.core.application.ports.dto.assembler;


import br.com.unesp.visitor_api.core.application.domain.entities.Address;
import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitorAssembler {
    public static Visitor dtoToEntity(VisitorDTO dto) {
        var entity = Visitor.builder()
                .name(dto.getName())
                .birthIn(dto.getBirthIn())
                .documentNumber(dto.getDocumentNumber())
                .contact(ContactAssembler.dtoToEntity(dto.getContact()))
                .access(AccessAssembler.dtoToEntity(dto.getAccess()))
                .type(dto.getType())
                .build();

        Set<Address> addresses = dto.getAddresses()
                .stream()
                .map(AddressAssembler::dtoToEntity)
                .collect(Collectors.toSet());

        entity.addAllAddresses(addresses);

        return entity;
    }
}

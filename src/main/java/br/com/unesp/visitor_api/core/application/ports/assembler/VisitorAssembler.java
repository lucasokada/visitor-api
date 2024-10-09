package br.com.unesp.visitor_api.core.application.ports.assembler;

import br.com.unesp.visitor_api.core.application.domain.Visitor;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.AddressEntity;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.VisitorEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitorAssembler {
    public static VisitorEntity dtoToEntity(VisitorDTO dto) {
        var entity = VisitorEntity.builder()
                .name(dto.getName())
                .birthIn(dto.getBirthIn())
                .documentNumber(dto.getDocumentNumber())
                .contact(ContactAssembler.dtoToEntity(dto.getContact()))
                .access(AccessAssembler.dtoToEntity(dto.getAccess()))
                .type(dto.getType())
                .build();

        Set<AddressEntity> addresses = dto.getAddresses()
                .stream()
                .map(AddressAssembler::dtoToEntity)
                .collect(Collectors.toSet());

        entity.addAllAddresses(addresses);

        return entity;
    }

    public static Visitor entityToDomain(VisitorEntity entity) {
        return Visitor.builder()
                .name(entity.getName())
                .birthIn(entity.getBirthIn())
                .documentNumber(entity.getDocumentNumber())
                .contact(ContactAssembler.entityToDomain(entity.getContact()))
                .access(AccessAssembler.entityToDomain(entity.getAccess()))
                .addresses(entity.getAddresses()
                        .stream()
                        .map(AddressAssembler::entityToDomain)
                        .collect(Collectors.toSet()))
                .id(Math.toIntExact(entity.getId()))
                .type(entity.getType())
                .build();
    }
}

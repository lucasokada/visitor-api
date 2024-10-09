package br.com.unesp.visitor_api.core.application.ports.assembler;

import br.com.unesp.visitor_api.core.application.domain.Access;
import br.com.unesp.visitor_api.core.application.ports.dto.AccessDTO;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.AccessEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessAssembler {
    public static AccessEntity dtoToEntity(AccessDTO dto) {
        return new AccessEntity(null, dto.getUser(), dto.getPassword());
    }

    public static Access entityToDomain(AccessEntity entity) {
        return new Access(entity.getUser(), entity.getPassword());
    }
}

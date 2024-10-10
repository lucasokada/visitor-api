package br.com.unesp.visitor_api.core.application.ports.dto.assembler;

import br.com.unesp.visitor_api.core.application.domain.entities.Access;
import br.com.unesp.visitor_api.core.application.ports.dto.AccessDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessAssembler {
    public static Access dtoToEntity(AccessDTO dto) {
        return new Access(null, dto.getUser(), dto.getPassword());
    }
}

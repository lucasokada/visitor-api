package br.com.unesp.visitor_api.mocks.dto;

import br.com.unesp.visitor_api.core.application.ports.dto.AccessDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessDTOMock {
    public static AccessDTO mock() {
        return new AccessDTO("visitor1", "passVisitor1");
    }
}

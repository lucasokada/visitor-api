package br.com.unesp.visitor_api.mocks.entities;

import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.AccessEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessEntityMock {
    public static AccessEntity mockWithoutId() {
        return new AccessEntity(null, "visitor1", "passVisitor1");
    }

    public static AccessEntity mockWithId() {
        return new AccessEntity(1L, "visitor1", "passVisitor1");

    }
}

package br.com.unesp.visitor_api.mocks.entities;

import br.com.unesp.visitor_api.core.application.domain.entities.Access;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessMock {
    public static Access mockWithoutId() {
        return new Access(null, "visitor1", "passVisitor1");
    }

    public static Access mockWithId() {
        return new Access(1L, "visitor1", "passVisitor1");

    }
}

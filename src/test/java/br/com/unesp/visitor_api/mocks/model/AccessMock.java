package br.com.unesp.visitor_api.mocks.model;

import br.com.unesp.visitor_api.core.application.domain.Access;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessMock {
    public static Access mock() {
        return new Access("visitor1", "passVisitor1");
    }
}

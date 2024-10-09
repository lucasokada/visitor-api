package br.com.unesp.visitor_api.mocks.model;

import br.com.unesp.visitor_api.core.application.domain.Contact;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactMock {
    public static Contact mock() {
        return new Contact("1933616242", "1627893624", "16979072524", "visitor1@email.com");
    }
}

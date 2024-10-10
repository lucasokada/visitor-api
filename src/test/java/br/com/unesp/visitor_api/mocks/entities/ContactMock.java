package br.com.unesp.visitor_api.mocks.entities;

import br.com.unesp.visitor_api.core.application.domain.entities.Contact;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactMock {
    public static Contact mockWithoutId() {
        return new Contact(null, "1933616242", "1627893624", "16979072524", "visitor1@email.com");
    }

    public static Contact mockWithId() {
        return new Contact(1L, "1933616242", "1627893624", "16979072524", "visitor1@email.com");
    }
}

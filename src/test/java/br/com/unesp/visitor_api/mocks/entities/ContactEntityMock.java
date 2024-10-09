package br.com.unesp.visitor_api.mocks.entities;

import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.ContactEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactEntityMock {
    public static ContactEntity mockWithoutId() {
        return new ContactEntity(null, "1933616242", "1627893624", "16979072524", "visitor1@email.com");
    }

    public static ContactEntity mockWithId() {
        return new ContactEntity(1L, "1933616242", "1627893624", "16979072524", "visitor1@email.com");
    }
}

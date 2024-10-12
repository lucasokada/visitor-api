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

    public static Contact mockEdited() {
        return new Contact(1L, "1733355245", "1932564345", "19984487897", "editedVisitor1@email.com");
    }

    public static Contact mockPartialEdited() {
        return new Contact(1L, "1933616242", "1627893624", "19984487897", "editedVisitor1@email.com");
    }
}

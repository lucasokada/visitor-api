package br.com.unesp.visitor_api.mocks.dto;

import br.com.unesp.visitor_api.core.application.ports.dto.ContactDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactDTOMock {
    public static ContactDTO mock() {
        return new ContactDTO("1933616242", "1627893624", "16979072524", "visitor1@email.com");
    }
}

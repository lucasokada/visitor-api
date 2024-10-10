package br.com.unesp.visitor_api.core.application.ports.dto.assembler;

import br.com.unesp.visitor_api.core.application.domain.entities.Contact;
import br.com.unesp.visitor_api.core.application.ports.dto.ContactDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactAssembler {
    public static Contact dtoToEntity(ContactDTO dto) {
        return new Contact(null, dto.getResidentialPhone(), dto.getCommercialPhone(), dto.getCellPhone(), dto.getEmail());
    }
}

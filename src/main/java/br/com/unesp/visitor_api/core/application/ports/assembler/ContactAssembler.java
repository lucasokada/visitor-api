package br.com.unesp.visitor_api.core.application.ports.assembler;

import br.com.unesp.visitor_api.core.application.domain.Contact;
import br.com.unesp.visitor_api.core.application.ports.dto.ContactDTO;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.ContactEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContactAssembler {
    public static ContactEntity dtoToEntity(ContactDTO dto) {
        return new ContactEntity(null, dto.getResidentialPhone(), dto.getCommercialPhone(), dto.getCellPhone(), dto.getEmail());
    }

    public static Contact entityToDomain(ContactEntity entity) {
        return new Contact(entity.getResidentialPhone(), entity.getCommercialPhone(), entity.getCellPhone(), entity.getEmail());
    }
}

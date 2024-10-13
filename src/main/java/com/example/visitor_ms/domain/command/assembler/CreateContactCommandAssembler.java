package com.example.visitor_ms.domain.command.assembler;

import com.example.visitor_ms.domain.Contact;
import com.example.visitor_ms.domain.command.CreateContactCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateContactCommandAssembler {
    static Contact assemble(CreateContactCommand contactCommand) {
        return new Contact(contactCommand.getResidentialPhone(), contactCommand.getCommercialPhone(),
                contactCommand.getCellPhone(), contactCommand.getEmail());
    }
}

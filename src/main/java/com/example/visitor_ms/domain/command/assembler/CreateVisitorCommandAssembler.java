package com.example.visitor_ms.domain.command.assembler;

import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.command.CreateVisitorCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateVisitorCommandAssembler {
    public static Visitor assemble(CreateVisitorCommand visitorCommand) {
        return new Visitor(visitorCommand.getName(), visitorCommand.getDocumentNumber(), visitorCommand.getBornIn(),
                visitorCommand.getType(), CreateContactCommandAssembler.assemble(visitorCommand.getCreateContactCommand()),
                CreateAccessCommandAssembler.assemble(visitorCommand.getCreateAccessCommand()),
                CreateAddressCommandAssembler.assemble(visitorCommand.getCreateAddressCommands()));
    }
}

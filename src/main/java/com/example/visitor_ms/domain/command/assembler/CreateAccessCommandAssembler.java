package com.example.visitor_ms.domain.command.assembler;

import com.example.visitor_ms.domain.Access;
import com.example.visitor_ms.domain.command.CreateAccessCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAccessCommandAssembler {
    static Access assemble(CreateAccessCommand accessCommand) {
        return new Access(accessCommand.getUsername(), accessCommand.getPassword());
    }
}

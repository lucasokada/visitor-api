package com.example.visitor_ms.domain.command.assembler;

import com.example.visitor_ms.domain.Address;
import com.example.visitor_ms.domain.command.CreateAddressCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAddressCommandAssembler {
    static Set<Address> assemble(List<CreateAddressCommand> addressCommands) {
        return addressCommands
                .stream()
                .map(CreateAddressCommandAssembler::assemble)
                .collect(Collectors.toSet());
    }

    static Address assemble(CreateAddressCommand addressCommand) {
        return new Address(addressCommand.getStreet(), addressCommand.getNumber(), addressCommand.getNeighborhood(),
                addressCommand.getZipCode(), addressCommand.getCity(), addressCommand.getState());
    }
}

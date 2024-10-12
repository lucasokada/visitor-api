package com.example.visitor_ms.domain.command;

import com.example.visitor_ms.domain.VisitorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateVisitorCommand {
    private String name;
    private String documentNumber;
    private LocalDate bornIn;
    private VisitorType type;
    private CreateContactCommand createContactCommand;
}

package com.example.visitor_ms.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreatePersonCommand {
    private String name;
    private String documentNumber;
    private LocalDate bornIn;
}

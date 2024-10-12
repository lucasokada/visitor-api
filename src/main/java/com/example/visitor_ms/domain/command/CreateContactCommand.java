package com.example.visitor_ms.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateContactCommand {
    private String residentialPhone;
    private String commercialPhone;
    private String cellPhone;
    private String email;
}

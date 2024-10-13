package com.example.visitor_ms.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateContactCommand {
    private String residentialPhone;
    private String commercialPhone;
    private String cellPhone;
    private String email;
}

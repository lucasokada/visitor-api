package com.example.visitor_ms.domain.command;

import com.example.visitor_ms.domain.BrazilState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAddressCommand {
    private String street;
    private Integer number;
    private String neighborhood;
    private String zipCode;
    private String city;
    private BrazilState state;
}

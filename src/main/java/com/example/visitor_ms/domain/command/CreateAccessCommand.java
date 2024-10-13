package com.example.visitor_ms.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccessCommand {
    private String username;
    private String password;
}

package com.example.visitor_ms.port.in.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DefaultApiResponse<T> {
    private String message;
    private T content;
}
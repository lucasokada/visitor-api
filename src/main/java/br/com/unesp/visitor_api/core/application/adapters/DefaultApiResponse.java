package br.com.unesp.visitor_api.core.application.adapters;

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

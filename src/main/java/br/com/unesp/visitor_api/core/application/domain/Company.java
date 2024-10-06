package br.com.unesp.visitor_api.core.application.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Company {
    @NotBlank
    @Schema(description = "nome da empresa")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[0-9]{14}$")
    @Schema(description = "CNPJ", example = "11111111111111")
    private String documentNumber;

    @NotNull
    @Schema(description = "trabalhadores")
    private List<Visitor> employees;
}

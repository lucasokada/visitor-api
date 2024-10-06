package br.com.unesp.visitor_api.core.application.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Person {
    @NotEmpty
    @Schema(description = "nome da pessoa")
    private String name;

    @NotNull
    @Schema(description = "data de nascimento")
    private LocalDate birthIn;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{9}$")
    @Schema(description = "RG", example = "111111111")
    private String documentNumber;

    @NotNull
    @Schema(description = "contato")
    private Contact contact;

    @NotNull
    @Schema(description = "informações de acesso")
    private Access access;

    @NotEmpty
    @Schema(description = "endereços")
    private List<Address> addresses;
}

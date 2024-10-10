package br.com.unesp.visitor_api.core.application.ports.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Valid
public class ContactDTO {
    @Pattern(regexp="(^$|^[0-9]{1,10}$)")
    @Schema(description = "telefone residencial", example = "XXXXYYYY")
    private String residentialPhone;

    @Pattern(regexp="(^$|^[0-9]{1,10}$)")
    @Schema(description = "telefone comercial", example = "XXXXYYYY")
    private String commercialPhone;

    @Pattern(regexp="(^$|^[0-9]{1,12}$)")
    @Schema(description = "telefone celular", example = "DDXXXXYYYY")
    private String cellPhone;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @Schema(description = "email", example = "user@email.com")
    private String email;
}

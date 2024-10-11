package br.com.unesp.visitor_api.core.application.ports.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
public class AccessDTO {
    @NotBlank
    @Schema(description = "usuário")
    private String user;

    @NotBlank
    @Schema(description = "senha")
    private String password;
}

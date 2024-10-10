package br.com.unesp.visitor_api.core.application.ports.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    @Schema(description = "usuário")
    private String user;

    @NotEmpty
    @Schema(description = "senha")
    private String password;
}

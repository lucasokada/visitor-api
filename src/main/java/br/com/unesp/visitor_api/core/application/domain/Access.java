package br.com.unesp.visitor_api.core.application.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Access {
    @NotEmpty
    @Schema(description = "usuário")
    private String user;

    @NotEmpty
    @Schema(description = "senha")
    private String password;
}

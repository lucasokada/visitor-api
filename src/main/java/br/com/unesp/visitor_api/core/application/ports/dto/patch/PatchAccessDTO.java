package br.com.unesp.visitor_api.core.application.ports.dto.patch;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Valid
public class PatchAccessDTO {
    @Schema(description = "usuário")
    private String user;

    @Schema(description = "senha")
    private String password;
}

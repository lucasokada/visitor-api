package br.com.unesp.visitor_api.core.application.ports.dto;

import br.com.unesp.visitor_api.core.application.domain.entities.enums.BrazilState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class AddressDTO {
    @NotBlank
    @Schema(description = "rua")
    private String street;

    @Schema(description = "número")
    private int number;

    @NotBlank
    @Schema(description = "bairro")
    private String neighborhood;

    @NotBlank
    @Pattern(regexp = "^[0-9]{8}$")
    @Schema(description = "CEP", example = "11111111")
    private String zipCode;

    @NotBlank
    @Schema(description = "cidade")
    private String city;

    @NotNull
    @Schema(description = "estado")
    private BrazilState state;
}

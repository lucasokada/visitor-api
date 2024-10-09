package br.com.unesp.visitor_api.core.application.ports.dto;

import br.com.unesp.visitor_api.core.application.domain.entities.enums.BrazilState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Valid
public class AddressDTO {
    @NotEmpty
    @Schema(description = "rua")
    private String street;

    @Schema(description = "número")
    private int number;

    @NotEmpty
    @Schema(description = "bairro")
    private String neighborhood;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{8}$")
    @Schema(description = "CEP", example = "11111111")
    private String zipCode;

    @NotEmpty
    @Schema(description = "cidade")
    private String city;

    @NotEmpty
    @Schema(description = "estado")
    private BrazilState state;
}

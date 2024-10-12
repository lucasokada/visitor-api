package br.com.unesp.visitor_api.core.application.ports.dto.patch;

import br.com.unesp.visitor_api.core.application.domain.entities.enums.BrazilState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
public class PatchAddressDTO {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "rua")
    private String street;

    @Schema(description = "número")
    private Integer number;

    @Schema(description = "bairro")
    private String neighborhood;

    @Pattern(regexp = "^[0-9]{8}$")
    @Schema(description = "CEP", example = "11111111")
    private String zipCode;

    @Schema(description = "cidade")
    private String city;

    @Schema(description = "estado")
    private BrazilState state;

    public boolean isNewAddress() {
        return this.id == null;
    }
}

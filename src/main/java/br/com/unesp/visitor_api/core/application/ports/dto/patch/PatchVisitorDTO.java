package br.com.unesp.visitor_api.core.application.ports.dto.patch;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Valid
public class PatchVisitorDTO {
    @Schema(description = "nome da pessoa")
    private String name;

    @Schema(description = "data de nascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthIn;

    @Pattern(regexp = "^[0-9]{9,11}$")
    @Schema(description = "RG ou CPF", example = "111111111")
    private String documentNumber;

    @Schema(description = "contato")
    private PatchContactDTO contact;

    @Schema(description = "informações de acesso")
    private PatchAccessDTO access;

    @Schema(description = "endereços")
    private List<PatchAddressDTO> addresses;
}

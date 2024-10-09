package br.com.unesp.visitor_api.core.application.domain;

import br.com.unesp.visitor_api.core.application.domain.model.enums.VisitorType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class Visitor extends Person {
    @Schema(description = "id do visitante")
    private int id;

    @NotNull
    @Schema(description = "tipo de visitante")
    private VisitorType type;

    public Visitor(@NotEmpty String name, @NotNull LocalDate birthIn, @NotEmpty @Pattern(regexp = "^[0-9]{9}$") String documentNumber, @NotNull Contact contact, @NotNull Access access, @NotEmpty Set<Address> addresses, int id, VisitorType type) {
        super(name, birthIn, documentNumber, contact, access, addresses);
        this.id = id;
        this.type = type;
    }
}

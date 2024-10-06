package br.com.unesp.visitor_api.core.application.domain;

import br.com.unesp.visitor_api.core.application.domain.model.enums.VisitorType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Visitor {
    @Schema(description = "id do visitante")
    private int id;

    @NotNull
    @Schema(description = "tipo de visitante")
    private VisitorType visitorType;
}

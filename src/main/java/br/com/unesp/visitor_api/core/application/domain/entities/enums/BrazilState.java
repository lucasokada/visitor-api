package br.com.unesp.visitor_api.core.application.domain.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BrazilState {
        AC("Acre"),
        AL("Alagoas"),
        AP("Amapá"),
        AM("Amazonas"),
        BA("Bahia"),
        CE("Ceará"),
        DF("Distrito Federal"),
        ES("Espírito Santo"),
        GO("Goiás"),
        MA("Maranhão"),
        MT("Mato Grosso"),
        MS("Mato Grosso do Sul"),
        MG("Minas Gerais"),
        PA("Pará"),
        PB("Paraíba"),
        PR("Paraná"),
        PE("Pernambuco"),
        PI("Piauí"),
        RJ("Rio de Janeiro"),
        RN("Rio Grande do Norte"),
        RS("Rio Grande do Sul"),
        RO("Rondônia"),
        RR("Roraima"),
        SC("Santa Catarina"),
        SP("São Paulo"),
        SE("Sergipe"),
        TO("Tocantins");

        private final String name;
}

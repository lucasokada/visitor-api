package br.com.unesp.visitor_api.mocks.entities;

import br.com.unesp.visitor_api.core.application.domain.entities.enums.BrazilState;
import br.com.unesp.visitor_api.core.application.domain.entities.Address;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMock {
    public static Set<Address> buildSetWithoutId() {
        return new HashSet<>() {{
            add(new Address(null, "Avenida 65", 155, "Jardim Itapuã", "13501618", "Rio Claro", BrazilState.SP, null));
            add(new Address(null, "Avenida 36", 65, "Jardim Bela Vista", "13504266", "Rio Claro", BrazilState.SP, null));
        }};
    }

    public static Set<Address> buildSetWithId() {
        return new HashSet<>() {{
            add(new Address(1L, "Avenida 65", 155, "Jardim Itapuã", "13501618", "Rio Claro", BrazilState.SP, null));
            add(new Address(2L, "Avenida 36", 65, "Jardim Bela Vista", "13504266", "Rio Claro", BrazilState.SP, null));
        }};
    }
}

package br.com.unesp.visitor_api.mocks.entities;

import br.com.unesp.visitor_api.core.application.domain.model.enums.BrazilState;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.AddressEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressEntityMock {
    public static Set<AddressEntity> buildSetWithoutId() {
        return new HashSet<>() {{
            add(new AddressEntity(null, "Avenida 65", 155, "Jardim Itapuã", "13501618", "Rio Claro", BrazilState.SP, null));
            add(new AddressEntity(null, "Avenida 36", 65, "Jardim Bela Vista", "13504266", "Rio Claro", BrazilState.SP, null));
        }};
    }

    public static Set<AddressEntity> buildSetWithId() {
        return new HashSet<>() {{
            add(new AddressEntity(1L, "Avenida 65", 155, "Jardim Itapuã", "13501618", "Rio Claro", BrazilState.SP, null));
            add(new AddressEntity(2L, "Avenida 36", 65, "Jardim Bela Vista", "13504266", "Rio Claro", BrazilState.SP, null));
        }};
    }
}

package br.com.unesp.visitor_api.mocks.model;

import br.com.unesp.visitor_api.core.application.domain.Address;
import br.com.unesp.visitor_api.core.application.domain.model.enums.BrazilState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMock {
    public static Set<Address> mockList() {
        return Set.of(
                new Address("Avenida 65", 155, "Jardim Itapuã", "13501618", "Rio Claro", BrazilState.SP),
                new Address("Avenida 36", 65, "Jardim Bela Vista", "13504266", "Rio Claro", BrazilState.SP)
        );
    }
}

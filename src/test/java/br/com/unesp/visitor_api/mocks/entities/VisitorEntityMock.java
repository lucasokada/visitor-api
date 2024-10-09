package br.com.unesp.visitor_api.mocks.entities;

import br.com.unesp.visitor_api.core.application.domain.model.enums.VisitorType;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.AddressEntity;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.entities.VisitorEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitorEntityMock {
    public static VisitorEntity mockWithoutId() {
        VisitorEntity visitor = new VisitorEntity(null, "visitor 1", LocalDate.of(1999,10, 7), "37055587069",
                ContactEntityMock.mockWithoutId(), AccessEntityMock.mockWithoutId(), new HashSet<>(), VisitorType.RELATED);

        Set<AddressEntity> addresses = AddressEntityMock.buildSetWithoutId();
        visitor.addAllAddresses(addresses);
        return visitor;
    }


    public static VisitorEntity mockWithId() {
        VisitorEntity visitor = new VisitorEntity(1L, "visitor 1", LocalDate.of(1999,10, 7), "37055587069",
                ContactEntityMock.mockWithId(), AccessEntityMock.mockWithId(), AddressEntityMock.buildSetWithId(), VisitorType.RELATED);

        Set<AddressEntity> addresses = AddressEntityMock.buildSetWithoutId();
        visitor.addAllAddresses(addresses);
        return visitor;
    }
}

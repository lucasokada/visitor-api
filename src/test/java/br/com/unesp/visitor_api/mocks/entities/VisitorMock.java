package br.com.unesp.visitor_api.mocks.entities;

import br.com.unesp.visitor_api.core.application.domain.entities.enums.VisitorType;
import br.com.unesp.visitor_api.core.application.domain.entities.Address;
import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitorMock {
    public static Visitor mockWithoutId() {
        Visitor visitor = new Visitor(null, "visitor 1", LocalDate.of(1999,10, 7), "37055587069",
                ContactMock.mockWithoutId(), AccessMock.mockWithoutId(), new HashSet<>(), VisitorType.RELATED);

        Set<Address> addresses = AddressMock.buildSetWithoutId();
        visitor.addAllAddresses(addresses);
        return visitor;
    }


    public static Visitor mockWithId() {
        Visitor visitor = new Visitor(1L, "visitor 1", LocalDate.of(1999,10, 7), "37055587069",
                ContactMock.mockWithId(), AccessMock.mockWithId(), AddressMock.buildSetWithId(), VisitorType.RELATED);

        Set<Address> addresses = AddressMock.buildSetWithoutId();
        visitor.addAllAddresses(addresses);
        return visitor;
    }
}

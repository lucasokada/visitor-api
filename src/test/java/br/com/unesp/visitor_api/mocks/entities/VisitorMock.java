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

        Set<Address> addresses = AddressMock.mockSetWithoutId();
        visitor.addAllAddresses(addresses);
        return visitor;
    }


    public static Visitor mockWithId() {
        Visitor visitor = new Visitor(1L, "visitor 1", LocalDate.of(1999,10, 7), "37055587069",
                ContactMock.mockWithId(), AccessMock.mockWithId(), new HashSet<>(), VisitorType.RELATED);

        Set<Address> addresses = AddressMock.mockSetWithId();
        visitor.addAllAddresses(addresses);
        return visitor;
    }

    public static Visitor mockEdited() {
        Visitor visitor = new Visitor(1L, "visitor 1 edit", LocalDate.of(2024, 10, 11), "42540120040",
                ContactMock.mockEdited(), AccessMock.mockEdited(), new HashSet<>(), VisitorType.RELATED);

        Set<Address> addresses = AddressMock.mockEdited();
        visitor.addAllAddresses(addresses);
        return visitor;
    }

    public static Visitor mockEditedWithNewAddress() {
        Visitor visitor = new Visitor(1L, "visitor 1 edit", LocalDate.of(2024, 10, 11), "42540120040",
                ContactMock.mockEdited(), AccessMock.mockEdited(), new HashSet<>(), VisitorType.RELATED);

        Set<Address> addresses = AddressMock.mockEditedWithNew();
        visitor.addAllAddresses(addresses);
        return visitor;
    }

    public static Visitor mockPartialEdited() {
        Visitor visitor = new Visitor(1L, "visitor", LocalDate.of(2024, 10, 11), "37055587069",
                ContactMock.mockPartialEdited(), AccessMock.mockPartialEdited(), new HashSet<>(), VisitorType.RELATED);

        Set<Address> addresses = AddressMock.mockPartialEdited();
        visitor.addAllAddresses(addresses);
        return visitor;
    }
}

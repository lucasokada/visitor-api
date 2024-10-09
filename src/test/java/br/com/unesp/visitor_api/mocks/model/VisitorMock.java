package br.com.unesp.visitor_api.mocks.model;

import br.com.unesp.visitor_api.core.application.domain.Visitor;
import br.com.unesp.visitor_api.core.application.domain.entities.enums.VisitorType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitorMock {
    public static Visitor mock() {
        return new Visitor("visitor 1", LocalDate.of(1999, 10, 7),
                "37055587069", ContactMock.mock(), AccessMock.mock(), AddressMock.mockList(),
                1, VisitorType.RELATED);
    }
}

package br.com.unesp.visitor_api.mocks.dto;

import br.com.unesp.visitor_api.core.application.domain.entities.enums.VisitorType;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitorDTOMock {
    public static VisitorDTO mock() {
        return new VisitorDTO("visitor 1", LocalDate.of(1999, 10, 7),
                "37055587069", ContactDTOMock.mock(), AccessDTOMock.mock(), AddressDTOMock.mockList(),
                VisitorType.RELATED);
    }
}

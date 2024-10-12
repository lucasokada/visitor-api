package br.com.unesp.visitor_api.core.application.usecases.visitor.builder;

import br.com.unesp.visitor_api.core.application.domain.entities.Address;
import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchAddressDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressBuilder {
    public static Address buildEdited(Address original, PatchAddressDTO target) {
        return Address.builder()
                .id(original.getId())
                .street(Objects.isNull(target.getStreet()) ? original.getStreet() : target.getStreet())
                .number(Objects.isNull(target.getNumber()) ? original.getNumber() : target.getNumber())
                .neighborhood(Objects.isNull(target.getNeighborhood()) ? original.getNeighborhood() : target.getNeighborhood())
                .zipCode(Objects.isNull(target.getZipCode()) ? original.getZipCode() : target.getZipCode())
                .city(Objects.isNull(target.getCity()) ? original.getCity() : target.getCity())
                .state(Objects.isNull(target.getState()) ? original.getState() : target.getState())
                .build();
    }
}

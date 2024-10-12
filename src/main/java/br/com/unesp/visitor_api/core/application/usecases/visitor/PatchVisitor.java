package br.com.unesp.visitor_api.core.application.usecases.visitor;

import br.com.unesp.visitor_api.core.application.domain.entities.Access;
import br.com.unesp.visitor_api.core.application.domain.entities.Address;
import br.com.unesp.visitor_api.core.application.domain.entities.Contact;
import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.dto.assembler.AddressAssembler;
import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchAccessDTO;
import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchAddressDTO;
import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchContactDTO;
import br.com.unesp.visitor_api.core.application.ports.dto.patch.PatchVisitorDTO;
import br.com.unesp.visitor_api.core.application.ports.in.PatchVisitorUseCase;
import br.com.unesp.visitor_api.core.application.ports.out.persistence.repositories.VisitorRepository;
import br.com.unesp.visitor_api.core.application.usecases.visitor.builder.AddressBuilder;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PatchVisitor implements PatchVisitorUseCase {
    private final VisitorRepository visitorRepository;

    @Override
    @Transactional
    public void patchVisitor(PatchVisitorDTO patchVisitorDTO, Long id) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Visitor with id %s not found", id)));
        Visitor edited = getEdited(visitor, patchVisitorDTO);
        visitorRepository.save(edited);
    }

    private Visitor getEdited(Visitor original, PatchVisitorDTO patchVisitorDTO) {
        return Visitor.builder()
                .id(original.getId())
                .name(Objects.isNull(patchVisitorDTO.getName()) ? original.getName() : patchVisitorDTO.getName())
                .birthIn(Objects.isNull(patchVisitorDTO.getBirthIn()) ? original.getBirthIn() : patchVisitorDTO.getBirthIn())
                .documentNumber(Objects.isNull(patchVisitorDTO.getDocumentNumber()) ? original.getDocumentNumber() : patchVisitorDTO.getDocumentNumber())
                .contact(getEditedContact(original.getContact(), patchVisitorDTO.getContact()))
                .access(getEditedAccess(original.getAccess(), patchVisitorDTO.getAccess()))
                .addresses(getEditedAddresses(original.getAddresses(), patchVisitorDTO.getAddresses()))
                .type(original.getType())
                .build();
    }

    private Contact getEditedContact(Contact original, PatchContactDTO patchContactDTO) {
        return Contact.builder()
                .id(original.getId())
                .residentialPhone(Objects.isNull(patchContactDTO.getResidentialPhone()) ? original.getResidentialPhone() : patchContactDTO.getResidentialPhone())
                .commercialPhone(Objects.isNull(patchContactDTO.getCommercialPhone()) ? original.getCommercialPhone() : patchContactDTO.getCommercialPhone())
                .cellPhone(Objects.isNull(patchContactDTO.getCellPhone()) ? original.getCellPhone() : patchContactDTO.getCellPhone())
                .email(Objects.isNull(patchContactDTO.getEmail()) ? original.getEmail() : patchContactDTO.getEmail())
                .build();
    }

    private Access getEditedAccess(Access original, PatchAccessDTO patchAccessDTO) {
        return Access.builder()
                .id(original.getId())
                .user(Objects.isNull(patchAccessDTO.getUser()) ? original.getUser() : patchAccessDTO.getUser())
                .password(Objects.isNull(patchAccessDTO.getPassword()) ? original.getUser() : patchAccessDTO.getPassword())
                .build();
    }

    private Set<Address> getEditedAddresses(Set<Address> addresses, List<PatchAddressDTO> patchAddresses) {
        Set<Address> editedAddresses = new HashSet<>();
        patchAddresses.forEach(patchAddress -> {
            if(patchAddress.isNewAddress()) {
                Address newAddress = AddressAssembler.dtoToEntity(patchAddress);
                editedAddresses.add(newAddress);
            } else {
                Address matchingAddress = addresses
                        .stream()
                        .filter(e -> e.getId().equals(patchAddress.getId()))
                        .findFirst()
                        .orElseThrow();
                editedAddresses.add(AddressBuilder.buildEdited(matchingAddress, patchAddress));
            }
        });

        return editedAddresses;
    }
}

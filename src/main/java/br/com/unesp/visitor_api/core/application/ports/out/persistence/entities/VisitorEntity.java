package br.com.unesp.visitor_api.core.application.ports.out.persistence.entities;

import br.com.unesp.visitor_api.core.application.domain.model.enums.VisitorType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
@Getter
@Entity(name = "Visitor")
@Table(name = "visitor")
@PrimaryKeyJoinColumn(name = "person_id")
public class VisitorEntity extends PersonEntity {
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private VisitorType type = VisitorType.OTHER;

    public VisitorEntity(Long id, String name, LocalDate birthIn, String documentNumber, ContactEntity contact, AccessEntity access, Set<AddressEntity> addresses, VisitorType type) {
        super(id, name, birthIn, documentNumber, contact, access, addresses);
        this.type = type;
    }
}

package com.example.visitor_ms.port.in.db.visitor;

import com.example.visitor_ms.domain.Address;
import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.VisitorType;
import com.example.visitor_ms.port.in.db.company.CompanyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Visitor")
@Table(name = "visitor")
@PrimaryKeyJoinColumn(name = "person_id")
public class VisitorEntity extends IndividualPersonEntity {
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VisitorType type = VisitorType.OTHER;

    @Setter
    @ManyToOne
    @JoinColumn(name = "company_documentNumber")
    private CompanyEntity company;

    public VisitorEntity(Visitor visitor) {
        super(visitor.getDocumentNumber(), visitor.getName(), visitor.getBornAt(), new ContactEntity(visitor.getContact()),
                new AccessEntity(visitor.getAccess()), getAddresses(visitor.getAddresses()));
        this.type = visitor.getType();
    }

    public Visitor toDomain() {
        return new Visitor(this.getName(), this.getDocumentNumber(), this.getBirthIn(), this.getType(),
                this.getContact().toDomain(), this.getAccess().toDomain(), getDomainAddresses());
    }

    private static Set<AddressEntity> getAddresses(Set<Address> addresses) {
        return addresses
                .stream()
                .map(AddressEntity::new)
                .collect(Collectors.toSet());
    }

    private Set<Address> getDomainAddresses() {
        return this.getAddresses()
                .stream()
                .map(AddressEntity::toDomain)
                .collect(Collectors.toSet());
    }
}

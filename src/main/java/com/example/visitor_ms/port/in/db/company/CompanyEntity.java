package com.example.visitor_ms.port.in.db.company;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.port.in.db.visitor.VisitorEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(exclude = "serviceProviders")
@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @Column(name = "documentNumber")
    private String documentNumber;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VisitorEntity> serviceProviders = new HashSet<>();

    public CompanyEntity(Company company) {
        this.documentNumber = company.getDocumentNumber();
        this.name = company.getName();
        this.serviceProviders = company.getServiceProviders()
                .stream()
                .map(VisitorEntity::new)
                .collect(Collectors.toSet());
        this.serviceProviders.forEach(e -> e.setCompany(this));
    }

    public Company toDomain() {
        return new Company(this.name, this.documentNumber, getDomainServiceProviders());
    }

    private Set<Visitor> getDomainServiceProviders() {
        return this.serviceProviders
                .stream()
                .map(VisitorEntity::toDomain)
                .collect(Collectors.toSet());
    }
}

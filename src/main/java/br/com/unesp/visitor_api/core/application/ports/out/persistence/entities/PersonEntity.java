package br.com.unesp.visitor_api.core.application.ports.out.persistence.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "Person")
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthIn;

    @Column(name = "documentNumber", nullable = false, length = 9)
    private String documentNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private ContactEntity contact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "access_id", referencedColumnName = "id")
    private AccessEntity access;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<AddressEntity> addresses = new HashSet<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof PersonEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(birthIn, that.birthIn) && Objects.equals(documentNumber, that.documentNumber) && Objects.equals(contact, that.contact) && Objects.equals(access, that.access) && Objects.equals(addresses, that.addresses);
    }

    public void addAllAddresses(Set<AddressEntity> addresses) {
        if(this.addresses == null) {
            this.addresses = new HashSet<>();
        }

        this.addresses.addAll(addresses);
        addresses.forEach(address -> address.setPerson(this));
    }
}

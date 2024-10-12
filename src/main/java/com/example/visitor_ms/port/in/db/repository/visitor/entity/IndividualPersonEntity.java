package com.example.visitor_ms.port.in.db.repository.visitor.entity;

import com.example.visitor_ms.domain.Person;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "person")
public class IndividualPersonEntity {
    @Id
    private String documentNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private LocalDate birthIn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private ContactEntity contact;

    public IndividualPersonEntity(Person person) {
        this.documentNumber = person.getDocumentNumber();
        this.name = person.getName();
    }
}

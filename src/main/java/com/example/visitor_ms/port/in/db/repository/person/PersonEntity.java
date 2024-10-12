package com.example.visitor_ms.port.in.db.repository.person;

import com.example.visitor_ms.domain.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    private String documentNumber;

    @Column(name = "name")
    private String name;

    public PersonEntity(Person person) {
        this.documentNumber = person.getDocumentNumber();
        this.name = person.getName();
    }
}

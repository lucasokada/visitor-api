package com.example.visitor_ms.port.in.db.repository.visitor.entity;

import com.example.visitor_ms.domain.Contact;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "residentialPhone")
    private String residentialPhone;

    @Column(name = "commercialPhone")
    private String commercialPhone;

    @Column(name = "cellPhone")
    private String cellPhone;

    @Column(name = "email")
    private String email;

    public ContactEntity(Contact contact) {
        this.residentialPhone = contact.getResidentialPhone();
        this.commercialPhone = contact.getCommercialPhone();
        this.cellPhone = contact.getCellPhone();
        this.email = contact.getEmail();
    }

    Contact toDomain() {
        return new Contact(this.residentialPhone, this.commercialPhone, this.cellPhone, this.email);
    }
}

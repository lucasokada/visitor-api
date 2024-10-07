package br.com.unesp.visitor_api.core.application.ports.out.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "residentialPhone", length = 10)
    private String residentialPhone;

    @Column(name = "commercialPhone", length = 10)
    private String commercialPhone;

    @Column(name = "cellPhone", length = 10)
    private String cellPhone;

    @Column(name = "email", length = 10)
    private String email;
}

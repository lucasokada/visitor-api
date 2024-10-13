package com.example.visitor_ms.port.in.db.repository.visitor.entity;

import com.example.visitor_ms.domain.Address;
import com.example.visitor_ms.domain.BrazilState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "zipCode", nullable = false, length = 8)
    private String zipCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "brazilState", nullable = false)
    @Enumerated(EnumType.STRING)
    private BrazilState state;

    Address toDomain() {
        return new Address(this.street, this.number, this.neighborhood, this.zipCode, this.city, this.state);
    }
}

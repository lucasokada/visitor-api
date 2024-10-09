package br.com.unesp.visitor_api.core.application.ports.out.persistence.entities;

import br.com.unesp.visitor_api.core.application.domain.entities.enums.BrazilState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "person")
@ToString(exclude = "person")
@Getter
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

    @Setter
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

}

package br.com.unesp.visitor_api.core.application.domain.entities;

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

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "residentialPhone", length = 10)
    private String residentialPhone;

    @Column(name = "commercialPhone", length = 10)
    private String commercialPhone;

    @Column(name = "cellPhone", length = 12)
    private String cellPhone;

    @Column(name = "email")
    private String email;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Contact that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(residentialPhone, that.residentialPhone) && Objects.equals(commercialPhone, that.commercialPhone) && Objects.equals(cellPhone, that.cellPhone) && Objects.equals(email, that.email);
    }
}

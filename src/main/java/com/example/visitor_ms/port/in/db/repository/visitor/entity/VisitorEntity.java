package com.example.visitor_ms.port.in.db.repository.visitor.entity;

import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.VisitorType;
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
@Table(name = "visitor")
public class VisitorEntity extends IndividualPersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VisitorType type = VisitorType.OTHER;

    public Visitor toDomain() {
        return new Visitor(this.getName(), this.getDocumentNumber(), this.getBirthIn(), this.getType(),
                this.getContact().toDomain(), this.getAccess().toDomain());
    }
}

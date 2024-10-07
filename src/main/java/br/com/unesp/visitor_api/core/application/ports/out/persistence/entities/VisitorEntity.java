package br.com.unesp.visitor_api.core.application.ports.out.persistence.entities;

import br.com.unesp.visitor_api.core.application.domain.model.enums.VisitorType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Visitor")
@Table(name = "visitor")
@PrimaryKeyJoinColumn(name = "person_id")
public class VisitorEntity extends PersonEntity {
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private VisitorType type = VisitorType.OTHER;
}

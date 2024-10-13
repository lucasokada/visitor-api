package com.example.visitor_ms.port.in.db.repository.visitor.entity;

import com.example.visitor_ms.domain.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "access")
public class AccessEntity {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    Access toDomain() {
        return new Access(this.username, this.password);
    }
}

package com.example.brettagraphql2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}

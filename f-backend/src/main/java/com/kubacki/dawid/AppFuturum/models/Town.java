package com.kubacki.dawid.AppFuturum.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Towns")
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Town {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;
}

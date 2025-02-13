package com.kubacki.dawid.AppFuturum.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Keywords")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Keyword {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;
}

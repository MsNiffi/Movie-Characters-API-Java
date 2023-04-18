package com.example.assignmentwebapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id")
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(length = 600)
    private String description;
    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;
}

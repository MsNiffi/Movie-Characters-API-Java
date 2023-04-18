package com.example.assignmentwebapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;
    @Column(nullable = false, length = 150)
    private String title;
    @Column(nullable = false, length = 30)
    private String genre;
    @Column(nullable = false, length = 5)
    private int releaseYear;
    @Column(nullable = false, length = 50)
    private String director;
    @Column(nullable = false, length = 300)
    private String picture;
    @Column(nullable = false, length = 300)
    private String trailer;

    @ManyToMany
    @JoinTable(
            name = "character_movies",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "character_id") }
    )
    private Set<Character> characters;
    @ManyToOne
    @JoinColumn(name="franchise_id")
    private Franchise franchise;
}

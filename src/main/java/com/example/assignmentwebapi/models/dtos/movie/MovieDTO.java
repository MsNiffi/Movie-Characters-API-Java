package com.example.assignmentwebapi.models.dtos.movie;

import lombok.Data;

import java.util.Set;

@Data
public class MovieDTO {

    private int id;
    private String title;
    private String genre;
    private Integer releaseYear;
    private String director;
    private String picture;
    private String trailer;
    private Integer franchise;
    private Set<Integer> characters;
}

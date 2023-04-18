package com.example.assignmentwebapi.models.dtos.movie;

import lombok.Data;

@Data
public class MoviePostDTO {
    private String title;
    private String genre;
    private Integer releaseYear;
    private String director;
    private String picture;
    private String trailer;
}

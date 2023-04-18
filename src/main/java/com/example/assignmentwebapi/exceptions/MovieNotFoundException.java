package com.example.assignmentwebapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException{
    /**
     * Thrown if a movie with given ID does not exist
     *
     * @param id movie ID
     */
    public MovieNotFoundException(int id) {
        super("Movie with ID " + id + " does not exist");
    }
}

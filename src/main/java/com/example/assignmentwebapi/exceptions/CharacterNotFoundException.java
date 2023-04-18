package com.example.assignmentwebapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException{
    /**
     * Thrown if a character with given ID does not exist
     *
     * @param id character ID
     */
    public CharacterNotFoundException(int id) {
        super("Character with ID " + id + " does not exist");
    }
}

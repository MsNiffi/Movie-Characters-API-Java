package com.example.assignmentwebapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FranchiseNotFoundException extends RuntimeException{
    /**
     * Thrown if a franchise with given ID does not exist
     *
     * @param id franchise ID
     */
    public FranchiseNotFoundException(int id) {
        super("Franchise with ID " + id + " does not exist");
    }
}

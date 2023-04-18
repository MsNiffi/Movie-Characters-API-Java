package com.example.assignmentwebapi.services.franchise;

import com.example.assignmentwebapi.models.Character;
import com.example.assignmentwebapi.models.Franchise;
import com.example.assignmentwebapi.models.Movie;
import com.example.assignmentwebapi.services.CRUDService;

import java.util.Set;

public interface FranchiseService extends CRUDService<Franchise, Integer> {
    /**
     * Updates a list of movies for a franchise
     *
     * @param franchiseId franchise ID whose movies should be updated
     * @param movieIds movies that should replace the old set of movies
     */
    void updateMovies(int franchiseId, int[] movieIds);
    /**
     * Finds all movies for a franchise
     *
     * @param franchiseId franchise ID whose movies should be found
     * @return set of Movie objects
     */
    Set<Movie> findAllMovies(int franchiseId);

    /**
     * Finds all characters for a franchise
     *
     * @param franchiseId
     * @return set of characters
     */
    Set<Character> findAllCharacters(int franchiseId);
}

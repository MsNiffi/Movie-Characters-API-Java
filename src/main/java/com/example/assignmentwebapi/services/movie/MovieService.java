package com.example.assignmentwebapi.services.movie;

import com.example.assignmentwebapi.models.Character;
import com.example.assignmentwebapi.models.Movie;
import com.example.assignmentwebapi.services.CRUDService;

import java.util.Set;

public interface MovieService extends CRUDService<Movie, Integer> {
    /**
     * Updates the set of characters that appear in a given movie.
     *
     * @param movieId movie id whose movieCharacters should be updated
     * @param characterIds a set of MovieCharacter objects that will replace the old reference
     */
    public void updateCharacters(int movieId, int[] characterIds);
    /**
     * Finds all characters that appear in a given movie
     *
     * @param movieId movie id whose characters should be found
     * @return a set of MovieCharacter objects
     */
    public Set<Character> findAllCharacters(int movieId);
}

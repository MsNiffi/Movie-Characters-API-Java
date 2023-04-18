package com.example.assignmentwebapi.services.movie;

import com.example.assignmentwebapi.exceptions.CharacterNotFoundException;
import com.example.assignmentwebapi.exceptions.MovieNotFoundException;
import com.example.assignmentwebapi.models.Character;
import com.example.assignmentwebapi.models.Movie;
import com.example.assignmentwebapi.repositories.CharacterRepository;
import com.example.assignmentwebapi.repositories.FranchiseRepository;
import com.example.assignmentwebapi.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService{
    private final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;
    private final CharacterRepository characterRepository;
    private final FranchiseRepository franchiseRepository;

    public MovieServiceImpl(MovieRepository movieRepository,
                            CharacterRepository characterRepository, FranchiseRepository franchiseRepository) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
        this.franchiseRepository = franchiseRepository;
    }

    /**
     * Finds a Movie by its ID
     *
     * @param id movie ID that should be found
     * @throws MovieNotFoundException if a movie with given id doesn't exist
     * @return Movie object
     */
    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    /**
     * Finds all movies from the movie table.
     *
     * @return a collection of Movie objects
     */
    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * Adds a new movie to the database.
     *
     * @param entity an object representation of the new movie
     * @return a Movie object
     */
    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    /**
     * Updates an existing movie.
     *
     * @param entity an object representation of a movie that should be updated, containing the new values
     * @return the updated Movie object
     */
    @Override
    public void update(Movie entity) {
        movieRepository.save(entity);
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id movie ID that should be deleted
     */
    @Override
    public void deleteById(Integer id) {
        deleteMovieReference(id);
        movieRepository.deleteById(id);
    }

    @Override
    public Set<Character> findAllCharacters(int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        return movie.getCharacters();
    }

    @Override
    public void updateCharacters(int movieId, int[] characterIds) {
        // Get movie
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        // Get movies for all the ids passed. Need to exist.
        Set<Character> characters = new HashSet<>();
        for (int id: characterIds) {
            Character character = characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
            characters.add(character);
        }
        // Movie owns relationship (mappedBy is on character),
        // so we can update its characters.
        movie.setCharacters(characters);
        movieRepository.save(movie);
    }

    /**
     * Deletes movie reference from character object in case of movie deletion
     *
     * @param movieId movie ID that is being deleted
     */
    private void deleteMovieReference(int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        movie.getCharacters().forEach(character -> {
            Set<Movie> movies = character.getMovies();
            movies.remove(movie);
            character.setMovies(movies);
            characterRepository.save(character);
        });
    }
}

package com.example.assignmentwebapi.services.franchise;

import com.example.assignmentwebapi.exceptions.FranchiseNotFoundException;
import com.example.assignmentwebapi.exceptions.MovieNotFoundException;
import com.example.assignmentwebapi.models.Character;
import com.example.assignmentwebapi.models.Franchise;
import com.example.assignmentwebapi.models.Movie;
import com.example.assignmentwebapi.repositories.FranchiseRepository;
import com.example.assignmentwebapi.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class FranchiseServiceImpl implements FranchiseService{
    private final FranchiseRepository franchiseRepository;
    private final MovieRepository movieRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, MovieRepository movieRepository) {
        this.franchiseRepository = franchiseRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public void update(Franchise entity) {
        franchiseRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        removeFranchiseReferenceFromMovies(id);
        franchiseRepository.deleteById(id);
    }

    @Override
    public void updateMovies(int franchiseId, int[] movieIds) {
        // Get franchise
        Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
        // Get characters for all the ids passed
        Set<Movie> movies = new HashSet<>();
        for (int id: movieIds) {
            Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
            movies.add(movie);
        }
        // Set each movie's franchise
        movies.forEach(m -> {
            m.setFranchise(franchise);
        });
        franchiseRepository.save(franchise);
    }

    @Override
    public Set<Movie> findAllMovies(int franchiseId) {
        Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
        return franchise.getMovies();
    }

    @Override
    public Set<Character> findAllCharacters(int franchiseId) {
        Set<Character> characters = new HashSet<>();
        // Reuse get all movies for franchise method
        Set<Movie> movies = findAllMovies(franchiseId);
        for (Movie m: movies) {
            characters.addAll(m.getCharacters());
        }
        return characters;
    }

    /**
     * Removes franchise reference from Movie objects in case of Franchise deletion
     *
     * @param franchiseId franchise id that is being deleted
     */
    private void removeFranchiseReferenceFromMovies(int franchiseId) {
        Collection<Movie> franchiseMovies = movieRepository.findMoviesFromFranchise(franchiseId);
        if (franchiseMovies != null) {
            franchiseMovies.forEach(movie -> {
                        movie.setFranchise(null);
                        movieRepository.save(movie);
                    }
            );
        }
    }
}

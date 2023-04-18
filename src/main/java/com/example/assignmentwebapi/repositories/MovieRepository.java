package com.example.assignmentwebapi.repositories;

import com.example.assignmentwebapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT m FROM Movie m WHERE m.franchise.id = ?1")
    public Collection<Movie> findMoviesFromFranchise(int franchiseId);
}

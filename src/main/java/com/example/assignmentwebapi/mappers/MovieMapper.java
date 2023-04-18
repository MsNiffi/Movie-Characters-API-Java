package com.example.assignmentwebapi.mappers;

import com.example.assignmentwebapi.models.Character;
import com.example.assignmentwebapi.models.Movie;
import com.example.assignmentwebapi.models.dtos.movie.MovieDTO;
import com.example.assignmentwebapi.models.dtos.movie.MoviePostDTO;
import com.example.assignmentwebapi.models.dtos.movie.MovieUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

// Mapper for Movies

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    // Mappings from DTO to movie.
    public abstract Movie moviePostDtoToMovie(MoviePostDTO moviePostDTO);
    public abstract Movie movieUpdateDtoToMovie(MovieUpdateDTO movieUpdateDTO);
    // Mappings from movie to DTOs
    public abstract Collection<MovieDTO> movieToMovieDto(Collection<Movie> movies);
    @Mapping(target="characters", source="characters", qualifiedByName = "charactersToIds")
    @Mapping(target="franchise", source="franchise.id")
    public abstract MovieDTO movieToMovieDto(Movie movie);

    @Named("charactersToIds")
    Set<Integer> map(Set<Character> source) {
        if (source == null) return null;
        return source.stream().map(Character::getId
        ).collect(Collectors.toSet());
    }
}

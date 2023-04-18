package com.example.assignmentwebapi.mappers;

import com.example.assignmentwebapi.models.Franchise;
import com.example.assignmentwebapi.models.Movie;
import com.example.assignmentwebapi.models.dtos.franchise.FranchiseDTO;
import com.example.assignmentwebapi.models.dtos.franchise.FranchisePostDTO;
import com.example.assignmentwebapi.models.dtos.franchise.FranchiseUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

// Mapper for franchises

@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {
    // Mappings from DTO to franchise
    public abstract Franchise franchisePostDtoToFranchise(FranchisePostDTO franchisePostDTO);
    public abstract Franchise franchiseUpdateDtoToFranchise(FranchiseUpdateDTO franchiseUpdateDTO);
    // Mappings from franchise to DTO
    public abstract Collection<FranchiseDTO> franchiseToFranchiseDto(Collection<Franchise> franchises);
    @Mapping(target="movies", source="movies", qualifiedByName = "moviesToMovieIds")
    public abstract FranchiseDTO franchiseToFranchiseDto(Franchise franchise);

    @Named("moviesToMovieIds")
    Set<Integer> mapMoviesToIds(Set<Movie> source) {
        if (source == null) return null;
        return source.stream().map(
                m -> m.getId()
        ).collect(Collectors.toSet());
    }
}

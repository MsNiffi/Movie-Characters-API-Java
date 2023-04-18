package com.example.assignmentwebapi.mappers;

import com.example.assignmentwebapi.models.Character;
import com.example.assignmentwebapi.models.Movie;
import com.example.assignmentwebapi.models.dtos.character.CharacterDTO;
import com.example.assignmentwebapi.models.dtos.character.CharacterPostDTO;
import com.example.assignmentwebapi.models.dtos.character.CharacterUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

 // Mapper for characters

@Mapper(componentModel = "spring")
public abstract class CharacterMapper {
    // Mappings from DTO to character.
    public abstract Character characterPostDtoToCharacter(CharacterPostDTO characterDto);
    public abstract Character characterUpdateDtoToCharacter(CharacterUpdateDTO characterUpdateDTO);
    // Mappings from character to DTO
    @Mapping(target = "movies", source="movies", qualifiedByName = "moviesToIds")
    public abstract CharacterDTO characterToCharacterDto(Character movieCharacter);
    public abstract Collection<CharacterDTO> characterToCharacterDto(Collection<Character> characters);

    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if (source == null) return null;
        return source.stream().map(m -> m.getId()
        ).collect(Collectors.toSet());
    }
}


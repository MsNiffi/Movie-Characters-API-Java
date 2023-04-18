package com.example.assignmentwebapi.services.character;

import com.example.assignmentwebapi.exceptions.CharacterNotFoundException;
import com.example.assignmentwebapi.models.Character;
import com.example.assignmentwebapi.repositories.CharacterRepository;
import com.example.assignmentwebapi.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService{
    private final CharacterRepository characterRepository;
    private final MovieRepository movieRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository, MovieRepository movieRepository) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Character findById(Integer id) {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
    }

    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character add(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public void update(Character character) {
        characterRepository.save(character);
    }

    @Override
    public void deleteById(Integer id) {
        deleteCharacterReference(id);
        characterRepository.deleteById(id);
    }

    /**
     * Deletes character references from all character's Movies in case of Character deletion
     *
     * @param characterId character ID that is being deleted
     */

    private void deleteCharacterReference(int characterId) {
        Character character = characterRepository.findById(characterId).orElseThrow(() -> new CharacterNotFoundException(characterId));
        character.getMovies().forEach(movie -> {
            Set<Character> chars = movie.getCharacters();
            chars.remove(character);
            movie.setCharacters(chars);
            movieRepository.save(movie);
        });
    }
}

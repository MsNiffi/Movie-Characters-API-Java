package com.example.assignmentwebapi;

import com.example.assignmentwebapi.services.character.CharacterService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    private final CharacterService characterService;

    public AppRunner(CharacterService characterService){
        this.characterService = characterService;
    }

    @Override
    public void run (ApplicationArguments args) throws Exception{
        // System.out.println(characterService.addCharacter("Ole", "And", Gender.MALE,"The Duck","awsomepicture.org"));

        // System.out.print(characterService.getCharacterById(1));
    }
}

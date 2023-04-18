package com.example.assignmentwebapi.models.dtos.character;

import com.example.assignmentwebapi.models.enums.Gender;
import lombok.Data;

import java.util.Set;

@Data
public class CharacterDTO {

    private int id;
    private String fullName;
    private String alias;
    private Gender gender;
    private String picture;
    private Set<Integer> movies;

}

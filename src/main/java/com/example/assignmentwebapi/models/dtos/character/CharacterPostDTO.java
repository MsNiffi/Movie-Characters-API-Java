package com.example.assignmentwebapi.models.dtos.character;

import lombok.Data;
import com.example.assignmentwebapi.models.enums.Gender;


@Data
public class CharacterPostDTO {

    private String fullName;
    private String alias;
    private Gender gender;
    private String picture;
}

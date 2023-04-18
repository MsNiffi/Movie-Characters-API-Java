package com.example.assignmentwebapi.models.dtos.character;

import com.example.assignmentwebapi.models.enums.Gender;
import lombok.Data;

@Data
public class CharacterUpdateDTO {
    private int id;
    private String fullName;
    private String alias;
    private Gender gender;
    private String picture;
}

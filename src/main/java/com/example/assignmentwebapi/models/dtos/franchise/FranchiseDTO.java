package com.example.assignmentwebapi.models.dtos.franchise;

import lombok.Data;

import java.util.Set;

@Data
public class FranchiseDTO {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movies;
}

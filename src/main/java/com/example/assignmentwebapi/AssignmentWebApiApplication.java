package com.example.assignmentwebapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Movie Database API",
        description = "A REST API to provide access to movies, the characters that are in those movies, and the franchises those movies belong to. Created by Anette Londal & Adrian Friduson.",
        version = "1.0"
))
public class AssignmentWebApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(AssignmentWebApiApplication.class, args);

    }

}

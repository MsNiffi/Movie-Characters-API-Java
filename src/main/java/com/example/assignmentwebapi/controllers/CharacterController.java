package com.example.assignmentwebapi.controllers;

import com.example.assignmentwebapi.exceptions.error.ApiErrorResponse;
import com.example.assignmentwebapi.mappers.CharacterMapper;
import com.example.assignmentwebapi.models.Character;
import com.example.assignmentwebapi.models.dtos.character.CharacterDTO;
import com.example.assignmentwebapi.models.dtos.character.CharacterPostDTO;
import com.example.assignmentwebapi.models.dtos.character.CharacterUpdateDTO;
import com.example.assignmentwebapi.services.character.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/characters")
@Tag(name = "Characters", description = "Endpoints to interact with characters")
public class CharacterController {

    private final CharacterMapper characterMapper;
    private final CharacterService characterService;

    public CharacterController(CharacterMapper characterMapper, CharacterService characterService){
        this.characterMapper = characterMapper;
        this.characterService = characterService;
    }

    @Operation(summary = "Get all characters")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class)))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
    })
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(
                characterMapper.characterToCharacterDto(
                        characterService.findAll()
                ));
    }

    @Operation(summary = "Get character by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CharacterDTO.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
    })
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable int id) {
        return ResponseEntity.ok(
                characterMapper.characterToCharacterDto(
                        characterService.findById(id)
                ));
    }

    @Operation(summary = "Add a new character")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CharacterPostDTO.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    ))
    })
    @PostMapping
    public ResponseEntity add(@RequestBody CharacterPostDTO characterDto) {
        Character c = characterService.add(
                characterMapper.characterPostDtoToCharacter(characterDto)
        );
        URI location = URI.create("characters/" + c.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update an existing character")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody CharacterUpdateDTO characterDto, @PathVariable int id) {
        if (id != characterDto.getId())
            return ResponseEntity.badRequest().build();
        characterService.update(
                characterMapper.characterUpdateDtoToCharacter(characterDto)
        );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a character by its ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
    })
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

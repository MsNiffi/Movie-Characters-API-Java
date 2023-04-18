package com.example.assignmentwebapi.controllers;

import com.example.assignmentwebapi.exceptions.error.ApiErrorResponse;
import com.example.assignmentwebapi.mappers.CharacterMapper;
import com.example.assignmentwebapi.mappers.FranchiseMapper;
import com.example.assignmentwebapi.mappers.MovieMapper;
import com.example.assignmentwebapi.models.Franchise;
import com.example.assignmentwebapi.models.dtos.character.CharacterDTO;
import com.example.assignmentwebapi.models.dtos.franchise.FranchiseDTO;
import com.example.assignmentwebapi.models.dtos.franchise.FranchisePostDTO;
import com.example.assignmentwebapi.models.dtos.franchise.FranchiseUpdateDTO;
import com.example.assignmentwebapi.models.dtos.movie.MovieDTO;
import com.example.assignmentwebapi.services.franchise.FranchiseService;
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
@RequestMapping(path = "api/v1/franchises")
@Tag(name = "Franchises", description = "Endpoints to interact with franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final MovieMapper movieMapper;
    private final CharacterMapper characterMapper;

    public FranchiseController(
            FranchiseService franchiseService,
            FranchiseMapper franchiseMapper,
            MovieMapper movieMapper, CharacterMapper characterMapper) {

        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }

    @Operation(summary = "Get all franchises")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class)))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
    })
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(
                franchiseMapper.franchiseToFranchiseDto(
                        franchiseService.findAll()
                ));
    }

    @Operation(summary = "Get franchise by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FranchiseDTO.class))
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
                franchiseMapper.franchiseToFranchiseDto(
                        franchiseService.findById(id)
                ));
    }

    @Operation(summary = "Add a new franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FranchisePostDTO.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
    })
    @PostMapping
    public ResponseEntity add(@RequestBody FranchisePostDTO franchiseDto) {
        Franchise f = franchiseService.add(
                franchiseMapper.franchisePostDtoToFranchise(franchiseDto)
        );
        URI location = URI.create("franchises/" + f.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update a franchise")
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
    public ResponseEntity update(@PathVariable int id, @RequestBody FranchiseUpdateDTO franchiseDto) {
        if (id != franchiseDto.getId()) {
            return ResponseEntity.badRequest().build();
        }
        franchiseService.update(
                franchiseMapper.franchiseUpdateDtoToFranchise(franchiseDto)
        );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a franchise by its ID")
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
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update the movies that belong to a given franchise")
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
    @PutMapping("{id}/movies")
    public ResponseEntity updateMovies(@PathVariable int id, @RequestBody int[] movieIds) {
        franchiseService.updateMovies(id,movieIds);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get a list of all movies owned by a given franchise")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
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
    @GetMapping("{id}/movies")
    public ResponseEntity getAllMovies(@PathVariable int id) {
        return ResponseEntity.ok(
                movieMapper.movieToMovieDto(
                        franchiseService.findAllMovies(id)
                ));
    }

    @Operation(summary = "Get a list of all characters in a given franchise")
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
    @GetMapping("{id}/characters")
    public ResponseEntity getAllCharacters(@PathVariable int id) {
        return ResponseEntity.ok(
                characterMapper.characterToCharacterDto(
                        franchiseService.findAllCharacters(id)
                ));
    }
}

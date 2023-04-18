package com.example.assignmentwebapi.controllers;

import com.example.assignmentwebapi.exceptions.error.ApiErrorResponse;
import com.example.assignmentwebapi.mappers.CharacterMapper;
import com.example.assignmentwebapi.mappers.MovieMapper;
import com.example.assignmentwebapi.models.Movie;
import com.example.assignmentwebapi.models.dtos.character.CharacterDTO;
import com.example.assignmentwebapi.models.dtos.movie.MovieDTO;
import com.example.assignmentwebapi.models.dtos.movie.MoviePostDTO;
import com.example.assignmentwebapi.models.dtos.movie.MovieUpdateDTO;
import com.example.assignmentwebapi.services.movie.MovieService;
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
@RequestMapping(path = "api/v1/movies")
@Tag(name = "Movies", description = "Endpoints to interact with movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final CharacterMapper characterMapper;

    public MovieController(MovieService movieService,
                           MovieMapper movieMapper,
                           CharacterMapper characterMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }

    @Operation(summary = "Get all movies")
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
                    ))
    })
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(
                movieMapper.movieToMovieDto(
                        movieService.findAll()
                ));
    }

    @Operation(summary = "Get movie by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class))
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
                movieMapper.movieToMovieDto(
                        movieService.findById(id)
                ));
    }

    @Operation(summary = "Add a new movie")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "Created",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ApiErrorResponse.class)
                    )),
    })
    @PostMapping
    public ResponseEntity add(@RequestBody MoviePostDTO movieDto) {
        Movie m = movieService.add(
                movieMapper.moviePostDtoToMovie(movieDto)
        );
        URI location = URI.create("movies/" + m.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update a movie")
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
    public ResponseEntity update(@RequestBody MovieUpdateDTO movieDto, @PathVariable int id) {
        if (id != movieDto.getId()) {
            return ResponseEntity.badRequest().build();
        }
        movieService.update(
                movieMapper.movieUpdateDtoToMovie(movieDto)
        );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a movie by its ID")
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
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update the list of characters that appear in a given movie")
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
    @PutMapping("{id}/characters")
    public ResponseEntity updateCharacters(@PathVariable int id, @RequestBody int[] charactersIds) {
        movieService.updateCharacters(id, charactersIds);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get a list of all characters that appear in a given movie")
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
                        movieService.findAllCharacters(id)
                ));
    }
}

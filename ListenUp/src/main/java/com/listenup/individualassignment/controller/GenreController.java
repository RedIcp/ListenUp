package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.business.genre.*;
import com.listenup.individualassignment.configuration.security.isauthenticated.IsAuthenticated;
import com.listenup.individualassignment.dto.createdto.CreateGenreRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;

import lombok.RequiredArgsConstructor;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
@CrossOrigin(origins = "http://localhost:3000")
public class GenreController {
    private final CreateGenreUseCase createGenreUseCase;
    private final GetGenresUseCase getGenresUseCase;
    private final GetGenreSongsUseCase getGenreSongsUseCase;
    private final DeleteGenreUseCase deleteGenreUseCase;
    private final UpdateGenreUseCase updateGenreUseCase;

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genres = getGenresUseCase.getGenres();

        if(genres != null) {
            return ResponseEntity.ok().body(genres);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<GenreSongListDTO> getGenrePath(@PathVariable(value = "id") long id) {
        GenreSongListDTO genre = getGenreSongsUseCase.getGenreSongs(id);

        if(genre != null) {
            return ResponseEntity.ok().body(genre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping()
    public ResponseEntity<CreateGenreResponseDTO> createGenre(@RequestBody @Valid CreateGenreRequestDTO genreDTO) {
        CreateGenreResponseDTO genre = createGenreUseCase.addGenre(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable("id") long id, @RequestBody @Valid GenreDTO genreDTO) {
        genreDTO.setId(id);
        updateGenreUseCase.editGenre(genreDTO);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable int id) {
        deleteGenreUseCase.deleteGenre(id);
        return ResponseEntity.ok().build();
    }
}

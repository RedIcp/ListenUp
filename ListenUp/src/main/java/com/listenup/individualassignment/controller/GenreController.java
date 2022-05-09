package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.dto.createdto.CreateGenreDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.business.GenreService;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
@CrossOrigin(origins = "http://localhost:3000")
public class GenreController {
    private final GenreService management;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        List<GenreDTO> genres = management.getGenres();

        if(genres != null) {
            return ResponseEntity.ok().body(genres);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<GenreSongListDTO> getGenrePath(@PathVariable(value = "id") long id) {
        GenreSongListDTO genre = management.getGenreSongs(id);

        if(genre != null) {
            return ResponseEntity.ok().body(genre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<CreateGenreDTO> createGenre(@RequestBody @Valid CreateGenreDTO genreDTO) {
        CreateGenreDTO genre = management.addGenre(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }
    @PutMapping("{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable("id") long id, @RequestBody @Valid GenreDTO genreDTO) {
        genreDTO.setId(id);
        management.editGenre(genreDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable int id) {
        management.deleteGenre(id);
        return ResponseEntity.ok().build();
    }
}

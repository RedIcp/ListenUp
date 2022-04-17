package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.GenreService;
import com.listenup.individualassignment.dto.GenreDTO;
import com.listenup.individualassignment.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
@CrossOrigin(origins = "http://localhost:3000")
public class GenreController {
    private final GenreService management;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = management.getGenres();

        if(genres != null) {
            return ResponseEntity.ok().body(genres);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Genre> getGenrePath(@PathVariable(value = "id") int id) {
        Genre genre = management.getGenre(id);

        if(genre != null) {
            return ResponseEntity.ok().body(genre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO) {
        Genre genre = management.genreDTOConvertor(genreDTO);
        if (!management.addGenre(genre)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "Genre" + "/" + genre.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(genreDTO);
        }
    }
    @PutMapping()
    public ResponseEntity<GenreDTO> updateGenre(@RequestBody GenreDTO genreDTO) {
        Genre genre = management.genreDTOConvertor(genreDTO);
        if (management.editGenre(genre)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteGenre(@PathVariable int id) {
        management.deleteGenre(id);
        return ResponseEntity.ok().build();
    }
}

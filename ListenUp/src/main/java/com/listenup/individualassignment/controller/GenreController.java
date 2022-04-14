package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.GenreService;
import com.listenup.individualassignment.business.imp.GenreServiceImp;
import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.repository.GenreRepository;
import com.listenup.individualassignment.repository.imp.GenreRepositoryImp;
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
    public ResponseEntity<String> createGenre(@RequestBody Genre genre) {
        if (!management.addGenre(genre)){
            String entity =  "Wrong id!";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "Genre" + "/" + genre.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }
    @PutMapping()
    public ResponseEntity<String> updateGenre(@RequestBody Genre genre) {
        if (management.editGenre(genre)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid info.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteGenre(@PathVariable int id) {
        management.deleteGenre(id);
        return ResponseEntity.ok().build();
    }
}

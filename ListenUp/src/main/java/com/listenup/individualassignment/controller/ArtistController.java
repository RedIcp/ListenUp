package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.ArtistService;
import com.listenup.individualassignment.model.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtistController {
    private final ArtistService management;

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        List<Artist> artists = management.getArtists();

        if(artists != null) {
            return ResponseEntity.ok().body(artists);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Artist> getArtistPath(@PathVariable(value = "id") int id) {
        Artist artist = management.getArtist(id);

        if(artist != null) {
            return ResponseEntity.ok().body(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        if (!management.addArtist(artist)){
            return ResponseEntity.notFound().build();
        } else {
            String url = "Artist" + "/" + artist.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(artist);
        }
    }
    @PutMapping()
    public ResponseEntity<Artist> updateArtist(@RequestBody Artist artist) {
        if (management.editArtist(artist)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteArtist(@PathVariable int id) {
        management.deleteArtist(id);
        return ResponseEntity.ok().build();
    }
}

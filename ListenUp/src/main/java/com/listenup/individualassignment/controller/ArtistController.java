package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.ArtistManagement;
import com.listenup.individualassignment.business.imp.ArtistManagementImp;
import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.repository.ArtistRepository;
import com.listenup.individualassignment.repository.imp.ArtistRepositoryImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artists")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtistController {
    private ArtistRepository db = new ArtistRepositoryImp();
    private ArtistManagement management = new ArtistManagementImp(db);

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
    public ResponseEntity<String> createArtist(@RequestBody Artist artist) {
        if (!management.addArtist(artist)){
            String entity =  "Wrong id!";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "Artist" + "/" + artist.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }
    @PutMapping()
    public ResponseEntity<String> updateArtist(@RequestBody Artist artist) {
        if (management.editArtist(artist)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid info.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteArtist(@PathVariable int id) {
        management.deleteArtist(id);
        return ResponseEntity.ok().build();
    }
}

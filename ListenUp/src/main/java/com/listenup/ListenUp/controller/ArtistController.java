package com.listenup.ListenUp.controller;

import com.listenup.ListenUp.business.AlbumManagement;
import com.listenup.ListenUp.business.ArtistManagement;
import com.listenup.ListenUp.business.imp.AlbumManagementImp;
import com.listenup.ListenUp.business.imp.ArtistManagementImp;
import com.listenup.ListenUp.model.Album;
import com.listenup.ListenUp.model.Artist;
import com.listenup.ListenUp.persistence.DBAlbum;
import com.listenup.ListenUp.persistence.DBArtist;
import com.listenup.ListenUp.persistence.imp.DBAlbumImp;
import com.listenup.ListenUp.persistence.imp.DBArtistImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private DBArtist db = new DBArtistImp();
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
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
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
    public ResponseEntity<Artist> updateArtist(@RequestBody Artist artist) {
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

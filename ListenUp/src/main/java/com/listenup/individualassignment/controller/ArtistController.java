package com.listenup.individualassignment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.business.ArtistService;
import com.listenup.individualassignment.dto.CreateUpdate.ArtistDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
@CrossOrigin(origins = "http://localhost:3000")
public class  ArtistController {
    private final ArtistService management;

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        List<ArtistDTO> artists = management.getArtistDTOS();

        if(artists != null) {
            return ResponseEntity.ok().body(artists);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<ArtistSongListDTO> getArtistPath(@PathVariable(value = "id") int id) {
        ArtistSongListDTO artist = management.artistObjConvertor(management.getArtist(id));

        if(artist != null) {
            return ResponseEntity.ok().body(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artistDTO) {
        Artist artist = management.artistDTOConvertor(artistDTO);
        if (!management.addArtist(artist)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "Artist" + "/" + artist.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(artistDTO);
        }
    }
    @PutMapping()
    public ResponseEntity<ArtistDTO> updateArtist(@RequestBody ArtistDTO artistDTO) {
        Artist artist = management.artistDTOConvertor(artistDTO);
        if (management.editArtist(artist)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteArtist(@PathVariable int id) {
        management.deleteArtist(id);
        return ResponseEntity.ok().build();
    }
}

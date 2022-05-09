package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.dto.createdto.CreateArtistDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.business.ArtistService;
import com.listenup.individualassignment.dto.ArtistAlbumListDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
@CrossOrigin(origins = "http://localhost:3000")
public class  ArtistController {
    private final ArtistService management;

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        List<ArtistDTO> artists = management.getArtists();

        if(artists != null) {
            return ResponseEntity.ok().body(artists);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<ArtistSongListDTO> getArtistPath(@PathVariable(value = "id") long id) {
        ArtistSongListDTO artist = management.getArtistSongs(id);

        if(artist != null) {
            return ResponseEntity.ok().body(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}/albums")
    public ResponseEntity<ArtistAlbumListDTO> getArtistAlbumsPath(@PathVariable(value = "id") long id) {
        ArtistAlbumListDTO artist = management.getArtistAlbums(id);

        if(artist != null) {
            return ResponseEntity.ok().body(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<CreateArtistDTO> createArtist(@RequestBody @Valid CreateArtistDTO artistDTO) {
        CreateArtistDTO artist = management.addArtist(artistDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(artist);
    }
    @PutMapping("{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable("id") long id, @RequestBody @Valid ArtistDTO artistDTO) {
        artistDTO.setId(id);
        management.editArtist(artistDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteArtist(@PathVariable long id) {
        management.deleteArtist(id);
        return ResponseEntity.ok().build();
    }
}

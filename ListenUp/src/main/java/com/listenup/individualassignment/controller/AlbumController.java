package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.AlbumService;
import com.listenup.individualassignment.dto.AlbumDTO;
import com.listenup.individualassignment.model.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
@CrossOrigin(origins = "http://localhost:3000")
public class AlbumController {
    private final AlbumService management;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = management.getAlbums();

        if(albums != null) {
            return ResponseEntity.ok().body(albums);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Album> getAlbumPath(@PathVariable(value = "id") int id) {
        Album album = management.getAlbum(id);

        if(album != null) {
            return ResponseEntity.ok().body(album);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        Album album = management.albumDTOConvertor(albumDTO);
        if (!management.addAlbum(album)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "Album" + "/" + albumDTO.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(albumDTO);
        }
    }
    @PutMapping()
    public ResponseEntity<AlbumDTO> updateAlbum(@RequestBody AlbumDTO albumDTO) {
        Album album = management.albumDTOConvertor(albumDTO);
        if (management.editAlbum(album)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAlbum(@PathVariable int id) {
        management.deleteAlbum(id);
        return ResponseEntity.ok().build();
    }
}

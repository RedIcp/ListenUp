package com.listenup.individualassignment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.business.AlbumService;
import com.listenup.individualassignment.dto.createupdate.AlbumDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
@CrossOrigin(origins = "http://localhost:3000")
public class AlbumController {
    private final AlbumService management;

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<AlbumDTO> albums = management.getAlbumDTOs(management.getAlbums());

        if(albums != null) {
            return ResponseEntity.ok().body(albums);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<AlbumSongListDTO> getAlbumPath(@PathVariable(value = "id") int id) {
        AlbumSongListDTO album = management.albumObjConvertor(management.getAlbum(id));

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

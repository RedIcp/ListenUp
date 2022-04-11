package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.AlbumManagement;
import com.listenup.individualassignment.business.imp.AlbumManagementImp;
import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.repository.AlbumRepository;
import com.listenup.individualassignment.repository.imp.AlbumRepositoryImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/albums")
@CrossOrigin(origins = "http://localhost:3000")
public class AlbumController {
    private AlbumRepository db = new AlbumRepositoryImp();
    private AlbumManagement management = new AlbumManagementImp(db);

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
    public ResponseEntity<String> createAlbum(@RequestBody Album album) {
        if (!management.addAlbum(album)){
            String entity =  "Wrong id!";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "Album" + "/" + album.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }
    @PutMapping()
    public ResponseEntity<String> updateAlbum(@RequestBody Album album) {
        if (management.editAlbum(album)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid info.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteAlbum(@PathVariable int id) {
        management.deleteAlbum(id);
        return ResponseEntity.ok().build();
    }
}

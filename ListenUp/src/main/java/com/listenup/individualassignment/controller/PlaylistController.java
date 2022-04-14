package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.business.imp.PlaylistServiceImp;
import com.listenup.individualassignment.model.Playlist;
import com.listenup.individualassignment.repository.PlaylistRepository;
import com.listenup.individualassignment.repository.imp.PlaylistRepositoryImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaylistController {
    private final PlaylistService management;

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        List<Playlist> playlists = management.getPlaylists();

        if(playlists != null) {
            return ResponseEntity.ok().body(playlists);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Playlist> getPlaylistPath(@PathVariable(value = "id") int id) {
        Playlist playlist = management.getPlaylist(id);

        if(playlist != null) {
            return ResponseEntity.ok().body(playlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<String> createPlaylist(@RequestBody Playlist playlist) {
        if (!management.addPlaylist(playlist)){
            String entity =  "Wrong id!";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "Playlist" + "/" + playlist.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }
    @PutMapping()
    public ResponseEntity<String> updatePlaylist(@RequestBody Playlist playlist) {
        if (management.editPlaylist(playlist)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid info.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deletePlaylist(@PathVariable int id) {
        management.deletePlaylist(id);
        return ResponseEntity.ok().build();
    }
}

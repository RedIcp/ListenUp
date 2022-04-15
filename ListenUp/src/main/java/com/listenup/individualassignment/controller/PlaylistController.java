package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.model.Playlist;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        if (!management.addPlaylist(playlist)){
            return ResponseEntity.notFound().build();
        } else {
            String url = "Playlist" + "/" + playlist.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(playlist);
        }
    }
    @PutMapping()
    public ResponseEntity<String> updatePlaylist(@RequestBody Playlist playlist) {
        if (management.editPlaylist(playlist)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePlaylist(@PathVariable int id) {
        management.deletePlaylist(id);
        return ResponseEntity.ok().build();
    }
}

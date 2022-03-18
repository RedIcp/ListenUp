package com.listenup.ListenUp.controller;

import com.listenup.ListenUp.business.AlbumManagement;
import com.listenup.ListenUp.business.PlaylistManagement;
import com.listenup.ListenUp.business.imp.AlbumManagementImp;
import com.listenup.ListenUp.business.imp.PlaylistManagementImp;
import com.listenup.ListenUp.model.Album;
import com.listenup.ListenUp.model.Playlist;
import com.listenup.ListenUp.persistence.DBAlbum;
import com.listenup.ListenUp.persistence.DBPlaylist;
import com.listenup.ListenUp.persistence.imp.DBAlbumImp;
import com.listenup.ListenUp.persistence.imp.DBPlaylistImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    private DBPlaylist db = new DBPlaylistImp();
    private PlaylistManagement management = new PlaylistManagementImp(db);

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
            String entity =  "Wrong id!";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "Playlist" + "/" + playlist.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }
    @PutMapping()
    public ResponseEntity<Playlist> updatePlaylist(@RequestBody Playlist playlist) {
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

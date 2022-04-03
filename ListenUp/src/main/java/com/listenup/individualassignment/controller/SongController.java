package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.SongManagement;
import com.listenup.individualassignment.business.imp.SongManagementImp;
import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.persistence.DBSong;
import com.listenup.individualassignment.persistence.imp.DBSongImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/songs")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {
    private DBSong db = new DBSongImp();
    private SongManagement management = new SongManagementImp(db);

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = management.getSongs();

        if(songs != null) {
            return ResponseEntity.ok().body(songs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<Song> getSongPath(@PathVariable(value = "id") int id) {
        Song song = management.getSong(id);

        if(song != null) {
            return ResponseEntity.ok().body(song);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<String> createSong(@RequestBody Song song) {
        if (!management.addSong(song)){
            String entity =  "Wrong id!";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "Song" + "/" + song.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }
    @PutMapping()
    public ResponseEntity<String> updateSong(@RequestBody Song song) {
        if (management.editSong(song)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid info.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteSong(@PathVariable int id) {
        management.deleteSong(id);
        return ResponseEntity.ok().build();
    }
}

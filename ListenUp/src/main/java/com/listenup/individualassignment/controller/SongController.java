package com.listenup.individualassignment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.business.SongService;
import com.listenup.individualassignment.dto.CreateUpdate.SongDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {
    private final SongService management;

    @GetMapping
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<SongDTO> songs = management.getSongDTOs();

        if(songs != null) {
            return ResponseEntity.ok().body(songs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<SongDTO> getSongPath(@PathVariable(value = "id") int id) {
        SongDTO song = management.songObjConvertor(management.getSong(id));

        if(song != null) {
            return ResponseEntity.ok().body(song);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<SongDTO> createSong(@RequestBody SongDTO songDTO) {
        Song song = management.songDTOConvertor(songDTO);
        if (!management.addSong(song)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "Song" + "/" + song.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(songDTO);
        }
    }
    @PutMapping()
    public ResponseEntity<SongDTO> updateSong(@RequestBody SongDTO songDTO) {
        Song song = management.songDTOConvertor(songDTO);
        if (management.editSong(song)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteSong(@PathVariable int id) {
        management.deleteSong(id);
        return ResponseEntity.ok().build();
    }
}

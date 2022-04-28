package com.listenup.individualassignment.controller;

import java.net.URI;
import java.util.List;

import com.listenup.individualassignment.dto.createupdate.AlbumSongDTO;
import com.listenup.individualassignment.model.AlbumSong;
import com.listenup.individualassignment.model.SingleSong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.business.SongService;
import com.listenup.individualassignment.dto.createupdate.SingleSongDTO;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {
    private final SongService management;

    @GetMapping
    public ResponseEntity<List<SingleSongDTO>> getAllSongs() {
        List<SingleSongDTO> songs = SongDTOConverter.convertToSingleSongDTOList(management.getSongs());

        if(songs != null) {
            return ResponseEntity.ok().body(songs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<SingleSongDTO> getSongPath(@PathVariable(value = "id") int id) {
        SingleSongDTO song = SongDTOConverter.convertToSingleSongDTO(management.getSong(id));

        if(song != null) {
            return ResponseEntity.ok().body(song);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/singlesong")
    public ResponseEntity<SingleSongDTO> createSingleSong(@RequestBody SingleSongDTO songDTO) {
        SingleSong song = SongDTOConverter.convertToSingleSongModel(songDTO);
        if (!management.addSingleSong(song)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "Song" + "/" + song.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(songDTO);
        }
    }
    @PostMapping("/albumsong")
    public ResponseEntity<AlbumSongDTO> createSingleSong(@RequestBody AlbumSongDTO songDTO) {
        AlbumSong song = SongDTOConverter.convertToAlbumSongModel(songDTO);
        if (!management.addAlbumSong(song)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "Song" + "/" + song.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(songDTO);
        }
    }
    @PutMapping()
    public ResponseEntity<SingleSongDTO> updateSong(@RequestBody SingleSongDTO songDTO) {
        Song song = SongDTOConverter.convertToSingleSongModel(songDTO);
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

package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaylistController {
    private final PlaylistService management;

    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylists() {
        List<PlaylistDTO> playlists = management.getPlaylists();

        if(playlists != null) {
            return ResponseEntity.ok().body(playlists);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<PlaylistSongListDTO> getPlaylistPath(@PathVariable(value = "id") long id) {
        PlaylistSongListDTO playlist = management.getPlaylistSong(id);

        if(playlist != null) {
            return ResponseEntity.ok().body(playlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<CreatePlaylistResponseDTO> createPlaylist(@RequestBody @Valid CreatePlaylistRequestDTO playlistDTO) {
        CreatePlaylistResponseDTO playlist = management.addPlaylist(playlistDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlist);
    }
    @PutMapping("{id}/songs")
    public ResponseEntity<PlaylistSongListDTO> addSongToPlaylist(@PathVariable("id") long id, @RequestBody @Valid PlaylistSongListDTO playlistDTO) {
        playlistDTO.setId(id);
        management.editPlaylistSongs(playlistDTO);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@PathVariable("id") long id, @RequestBody @Valid PlaylistDTO playlistDTO) {
        playlistDTO.setId(id);
        management.editPlaylist(playlistDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePlaylist(@PathVariable long id) {
        management.deletePlaylist(id);
        return ResponseEntity.ok().build();
    }
}

package com.listenup.individualassignment.controller;

import java.net.URI;
import java.util.List;

import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.model.Playlist;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.dto.createupdate.PlaylistDTO;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaylistController {
    private final PlaylistService management;

    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylists() {
        List<PlaylistDTO> playlists = PlaylistDTOConverter.convertToDTOList(management.getPlaylists());

        if(playlists != null) {
            return ResponseEntity.ok().body(playlists);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<PlaylistSongListDTO> getPlaylistPath(@PathVariable(value = "id") int id) {
        PlaylistSongListDTO playlist = PlaylistDTOConverter.convertToDTOForSong(management.getPlaylist(id));

        if(playlist != null) {
            return ResponseEntity.ok().body(playlist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        Playlist playlist = PlaylistDTOConverter.convertToModel(playlistDTO);
        if (!management.addPlaylist(playlist)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "Playlist" + "/" + playlist.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(playlistDTO);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<PlaylistSongListDTO> addSongToPlaylist(@PathVariable(value = "id") PlaylistSongListDTO playlistDTO) {
        Playlist playlist = management.getPlaylist(playlistDTO.getId());
        playlist.setSongs(SongDTOConverter.convertToSingleSongModelList(playlistDTO.getSongs()));
        if (management.editPlaylist(playlist)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PutMapping()
    public ResponseEntity<PlaylistDTO> updatePlaylist(@RequestBody PlaylistDTO playlistDTO) {
        Playlist playlist = PlaylistDTOConverter.convertToModel(playlistDTO);
        if (management.editPlaylist(playlist)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePlaylist(@PathVariable int id) {
        management.deletePlaylist(id);
        return ResponseEntity.ok().build();
    }
}

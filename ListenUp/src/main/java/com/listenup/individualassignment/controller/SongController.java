package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.business.song.*;
import com.listenup.individualassignment.configuration.security.isauthenticated.IsAuthenticated;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongResponseDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;

import lombok.RequiredArgsConstructor;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {
    private final CreateSingleSongUseCase createSingleSongUseCase;
    private final CreateAlbumSongUseCase createAlbumSongUseCase;
    private final DeleteSongUseCase deleteSongUseCase;
    private final UpdateSongUseCase updateSongUseCase;
    private final GetSongUseCase getSongUseCase;
    private final GetSongsUseCase getSongsUseCase;

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<SingleSongDTO>> getAllSongs() {
        List<SingleSongDTO> songs = getSongsUseCase.getSongs();

        if(songs != null) {
            return ResponseEntity.ok().body(songs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<SingleSongDTO> getSongPath(@PathVariable(value = "id") long id) {
        SingleSongDTO song = getSongUseCase.getSong(id);

        if(song != null) {
            return ResponseEntity.ok().body(song);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("/singlesong")
    public ResponseEntity<CreateSingleSongResponseDTO> createSingleSong(@RequestBody @Valid CreateSingleSongRequestDTO songDTO) {
        CreateSingleSongResponseDTO song = createSingleSongUseCase.addSingleSong(songDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(song);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("/albumsong")
    public ResponseEntity<CreateAlbumSongResponseDTO> createAlbumSong(@RequestBody @Valid CreateAlbumSongRequestDTO songDTO) {
        CreateAlbumSongResponseDTO song = createAlbumSongUseCase.addAlbumSong(songDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(song);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<SingleSongDTO> updateSong(@PathVariable("id") long id, @RequestBody @Valid SingleSongDTO songDTO) {
        songDTO.setId(id);
        updateSongUseCase.editSong(songDTO);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteSong(@PathVariable int id) {
        deleteSongUseCase.deleteSong(id);
        return ResponseEntity.ok().build();
    }
}

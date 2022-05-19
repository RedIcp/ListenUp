package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.configuration.security.isauthenticated.IsAuthenticated;
import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.business.AlbumService;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;

import lombok.RequiredArgsConstructor;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
@CrossOrigin(origins = "http://localhost:3000")
public class AlbumController {
    private final AlbumService management;

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<AlbumDTO> albums = management.getAlbums();

        if(albums != null) {
            return ResponseEntity.ok().body(albums);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<AlbumSongListDTO> getAlbumPath(@PathVariable(value = "id") long id) {
        AlbumSongListDTO album = management.getAlbumSongs(id);

        if(album != null) {
            return ResponseEntity.ok().body(album);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping()
    public ResponseEntity<CreateAlbumResponseDTO> createAlbum(@RequestBody @Valid CreateAlbumRequestDTO albumDTO) {
        CreateAlbumResponseDTO album = management.addAlbum(albumDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(album);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable("id") long id, @RequestBody @Valid AlbumDTO albumDTO) {
        albumDTO.setId(id);
        management.editAlbum(albumDTO);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAlbum(@PathVariable long id) {
        management.deleteAlbum(id);
        return ResponseEntity.ok().build();
    }
}

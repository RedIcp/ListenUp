package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.business.artist.*;
import com.listenup.individualassignment.configuration.security.isauthenticated.IsAuthenticated;
import com.listenup.individualassignment.dto.createdto.CreateArtistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.dto.ArtistAlbumListDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;

import lombok.RequiredArgsConstructor;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/artists")
@CrossOrigin(origins = "http://localhost:3000")
public class  ArtistController {
    private final CreateArtistUseCase createArtistUseCase;
    private final GetArtistsUseCase getArtistsUseCase;
    private final GetArtistSongsUseCase getArtistSongsUseCase;
    private final GetArtistAlbumsUseCase getArtistAlbumsUseCase;
    private final DeleteArtistUseCase deleteArtistUseCase;
    private final UpdateArtistUseCase updateArtistUseCase;


    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        List<ArtistDTO> artists = getArtistsUseCase.getArtists();

        if(artists != null) {
            return ResponseEntity.ok().body(artists);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<ArtistSongListDTO> getArtistPath(@PathVariable(value = "id") long id) {
        ArtistSongListDTO artist = getArtistSongsUseCase.getArtistSongs(id);

        if(artist != null) {
            return ResponseEntity.ok().body(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping("{id}/albums")
    public ResponseEntity<ArtistAlbumListDTO> getArtistAlbumsPath(@PathVariable(value = "id") long id) {
        ArtistAlbumListDTO artist = getArtistAlbumsUseCase.getArtistAlbums(id);

        if(artist != null) {
            return ResponseEntity.ok().body(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping()
    public ResponseEntity<CreateArtistResponseDTO> createArtist(@RequestBody @Valid CreateArtistRequestDTO artistDTO) {
        CreateArtistResponseDTO artist = createArtistUseCase.addArtist(artistDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(artist);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable("id") long id, @RequestBody @Valid ArtistDTO artistDTO) {
        artistDTO.setId(id);
        updateArtistUseCase.editArtist(artistDTO);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteArtist(@PathVariable long id) {
        deleteArtistUseCase.deleteArtist(id);
        return ResponseEntity.ok().build();
    }
}

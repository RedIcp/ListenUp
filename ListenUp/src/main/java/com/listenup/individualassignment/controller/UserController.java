package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.configuration.security.isauthenticated.IsAuthenticated;
import com.listenup.individualassignment.dto.*;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;

import lombok.RequiredArgsConstructor;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService management;

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<ViewUserDTO>> getAllUsers() {
        List<ViewUserDTO> users = management.getUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping("{id}/profile")
    public ResponseEntity<UpdateUserDTO> getUserPath(@PathVariable(value = "id") long id) {
        UpdateUserDTO dto = management.getUser(id);
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping("{id}/playlists")
    public ResponseEntity<CustomerPlaylistListDTO> getCustomerPlaylistsPath(@PathVariable(value = "id") long id) {
        CustomerPlaylistListDTO dto = management.getCustomerPlaylists(id);
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping("{id}/collection")
    public ResponseEntity<CustomerLikedSongListDTO> getCustomerLikedSongsPath(@PathVariable(value = "id") long id) {
        CustomerLikedSongListDTO dto = management.getCustomerCollection(id);
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @GetMapping("{id}/likedplaylists")
    public ResponseEntity<CustomerLikedPlaylistListDTO> getCustomerLikedPlaylistsPath(@PathVariable(value = "id") long id) {
        CustomerLikedPlaylistListDTO dto = management.getCustomerLikedPlaylists(id);
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @PutMapping("{id}/profile")
    public ResponseEntity<UpdateUserDTO> updateUser(@PathVariable("id") long id, @RequestBody @Valid UpdateUserDTO userDTO) {
        userDTO.setId(id);
        management.updateAccount(userDTO);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @PutMapping("{id}/collection")
    public ResponseEntity<CustomerLikedSongListDTO> editSongInCollection(@PathVariable("id") long id, @RequestBody @Valid AddRemoveSongToCollectionDTO song) {
        song.setCustomerID(id);
        management.editUserCollection(song);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER"})
    @PutMapping("{id}/likedplaylist")
    public ResponseEntity<CustomerLikedPlaylistListDTO> editLikedPlaylist(@PathVariable("id") long id, @RequestBody @Valid AddRemoveLikedPlaylistDTO playlist) {
        playlist.setCustomerID(id);
        management.editUserLikedPlaylists(playlist);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        management.deleteAccount(id);
        return ResponseEntity.ok().build();

    }
}

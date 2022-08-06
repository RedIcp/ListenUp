package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.business.user.account.CreateAccountUseCase;
import com.listenup.individualassignment.business.user.account.DeleteAccountUseCase;
import com.listenup.individualassignment.business.user.account.GetUserUseCase;
import com.listenup.individualassignment.business.user.account.UpdateProfileUseCase;
import com.listenup.individualassignment.business.user.action.*;
import com.listenup.individualassignment.configuration.security.isauthenticated.IsAuthenticated;
import com.listenup.individualassignment.dto.*;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;

import lombok.RequiredArgsConstructor;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UpdateProfileUseCase updateProfileUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final GetUserUseCase getUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserPlaylistsUseCase getCustomerPlaylistsUseCase;
    private final GetUserLikedPlaylistsUseCase getUserLikedPlaylistsUseCase;
    private final GetUserLikedSongsUseCase getUserLikedSongsUseCase;
    private final LikeUnlikeSongUseCase likeUnlikeSongUseCase;
    private final LikeUnlikePlaylistUseCase likeUnlikePlaylistUseCase;

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<ViewUserDTO>> getAllUsers() {
        List<ViewUserDTO> users = getUsersUseCase.getUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/profile")
    public ResponseEntity<UpdateUserDTO> getUserPath(@PathVariable(value = "id") long id) {
        UpdateUserDTO dto = getUserUseCase.getUser(id);
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
        CustomerPlaylistListDTO dto = getCustomerPlaylistsUseCase.getCustomerPlaylists(id);
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
        CustomerLikedSongListDTO dto = getUserLikedSongsUseCase.getCustomerCollection(id);
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
        CustomerLikedPlaylistListDTO dto = getUserLikedPlaylistsUseCase.getCustomerLikedPlaylists(id);
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
        updateProfileUseCase.updateAccount(userDTO);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @PutMapping("{id}/collection")
    public ResponseEntity<CustomerLikedSongListDTO> likeUnlikeSong(@PathVariable("id") long id, @RequestBody @Valid AddRemoveSongToCollectionDTO song) {
        song.setCustomerID(id);
        likeUnlikeSongUseCase.likeUnlikeSong(song);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @PutMapping("{id}/library")
    public ResponseEntity<CustomerLikedPlaylistListDTO> addLikedPlaylist(@PathVariable("id") long id, @RequestBody @Valid AddRemoveLikedPlaylistDTO playlist) {
        playlist.setCustomerID(id);
        likeUnlikePlaylistUseCase.likeUnlikePlaylist(playlist);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_ADMIN"})
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        deleteAccountUseCase.deleteAccount(id);
        return ResponseEntity.ok().build();

    }
}

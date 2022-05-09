package com.listenup.individualassignment.controller;

import java.util.List;

import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.dto.vieweditdto.UserDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService management;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = management.getUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}/profile")
    public ResponseEntity<UserDTO> getUserPath(@PathVariable(value = "id") long id) {
        UserDTO dto = management.getUser(id);
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}/playlists")
    public ResponseEntity<CustomerPlaylistListDTO> getCustomerPlaylistsPath(@PathVariable(value = "id") long id) {
        CustomerPlaylistListDTO dto = management.getCustomerPlaylists(id);
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}/collection")
    public ResponseEntity<CustomerLikedSongListDTO> getCustomerLikedSongsPath(@PathVariable(value = "id") long id) {
        CustomerLikedSongListDTO dto = management.getCustomerCollection(id);
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}/likedplaylists")
    public ResponseEntity<CustomerLikedPlaylistListDTO> getCustomerLikedPlaylistsPath(@PathVariable(value = "id") long id) {
        CustomerLikedPlaylistListDTO dto = management.getCustomerLikedPlaylists(id);
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<CreateUserDTO> createUser(@RequestBody @Valid CreateUserDTO userDTO) {
        CreateUserDTO user = management.createAccount(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @PutMapping("{id}/profile")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") long id, @RequestBody @Valid UserDTO userDTO) {
        userDTO.setId(id);
        management.updateAccount(userDTO);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}/collection")
    public ResponseEntity<CustomerLikedSongListDTO> addSongToCollection(@PathVariable("id") long id, @RequestBody @Valid CustomerLikedSongListDTO userDTO) {
        userDTO.setId(id);
        management.editUserCollection(userDTO);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}/likedplaylist")
    public ResponseEntity<CustomerLikedPlaylistListDTO> addLikedPlaylist(@PathVariable("id") long id, @RequestBody @Valid CustomerLikedPlaylistListDTO userDTO) {
        userDTO.setId(id);
        management.editUserLikedPlaylists(userDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        management.deleteAccount(id);
        return ResponseEntity.ok().build();

    }
}

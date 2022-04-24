package com.listenup.individualassignment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.listenup.individualassignment.model.User;
import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.dto.createupdate.UserDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService management;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = management.getUserDTOs(management.getUsers());

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}/profile")
    public ResponseEntity<UserDTO> getUserPath(@PathVariable(value = "id") int id) {
        UserDTO dto = management.userObjConvertorForProfile(management.getUserByID(id));
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}/playlists")
    public ResponseEntity<CustomerPlaylistListDTO> getCustomerPlaylistsPath(@PathVariable(value = "id") int id) {
        CustomerPlaylistListDTO dto = management.customerObjConvertorForPlaylists((Customer)management.getUserByID(id));
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}/collection")
    public ResponseEntity<CustomerLikedSongListDTO> getCustomerLikedSongsPath(@PathVariable(value = "id") int id) {
        CustomerLikedSongListDTO dto = management.customerObjConvertorForLikedSongs((Customer)management.getUserByID(id));
        if(dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User customer = management.userDTOConvertor(userDTO);
        if (!management.createAccount(customer)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "user" + "/" + customer.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).body(userDTO);
        }
    }
    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        User customer = management.userDTOConvertor(userDTO);
        if (management.updateAccount(customer)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        management.deleteAccount(id);
        return ResponseEntity.ok().build();

    }
}

package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.dto.AccountDTO;
import com.listenup.individualassignment.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService management;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllUsers() {
        List<AccountDTO> users = management.getUserDTOs();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<AccountDTO> getUserPath(@PathVariable(value = "id") int id) {
        User user = management.getUserByID(id);
        AccountDTO dto = management.userObjConvertor(user);
        if(user != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<AccountDTO> createUser(@RequestBody AccountDTO userDTO) {
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
    public ResponseEntity<AccountDTO> updateUser(@RequestBody AccountDTO userDTO) {
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

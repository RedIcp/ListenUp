package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.business.imp.UserServiceImp;
import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.model.User;
import com.listenup.individualassignment.repository.UserRepository;
import com.listenup.individualassignment.repository.imp.UserRepositoryImp;
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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = management.getUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<User> getUserPath(@PathVariable(value = "id") int id) {
        User user = management.getUserByID(id);

        if(user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody Customer user) {
        if (!management.createAccount(user)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String url = "user" + "/" + user.getId();
            URI uri = URI.create(url);
            return ResponseEntity.created(uri).build();
        }
    }
    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestBody Customer user) {
        if (management.updateAccount(user)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid info.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        management.deleteAccount(id);
        return ResponseEntity.ok().build();

    }
}

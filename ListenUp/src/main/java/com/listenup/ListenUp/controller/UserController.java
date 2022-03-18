package com.listenup.ListenUp.controller;

import com.listenup.ListenUp.business.UserManagement;
import com.listenup.ListenUp.business.imp.UserManagementImp;
import com.listenup.ListenUp.model.User;
import com.listenup.ListenUp.persistence.DBUser;
import com.listenup.ListenUp.persistence.imp.DBUserImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private DBUser db = new DBUserImp();
    private UserManagement managment = new UserManagementImp(db);

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = managment.getUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<User> getUserPath(@PathVariable(value = "id") int id) {
        User student = managment.getUserByID(id);

        if(student != null) {
            return ResponseEntity.ok().body(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (!managment.createAccount(user)){
            String entity =  "User with this email " + user.getEmail() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "user" + "/" + user.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }
    }
    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (managment.updateAccount(user)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid country code.",HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable int id) {
        managment.deleteAccount(id);
        return ResponseEntity.ok().build();

    }
}

package com.listenup.ListenUp.controller;

import com.listenup.ListenUp.business.UserManagment;
import com.listenup.ListenUp.business.imp.UserManagmentImp;
import com.listenup.ListenUp.model.User;
import com.listenup.ListenUp.persistence.DBUser;
import com.listenup.ListenUp.persistence.imp.DBUserImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private DBUser db = new DBUserImp();
    private UserManagment managment = new UserManagmentImp(db);

    @GetMapping
    public ResponseEntity<List<User>> getAllStudents() {
        List<User> users = managment.getUsers();

        if(users != null) {
            return ResponseEntity.ok().body(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<User> getStudentPath(@PathVariable(value = "id") int id) {
        User student = managment.getUserByID(id);

        if(student != null) {
            return ResponseEntity.ok().body(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

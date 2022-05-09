package com.listenup.individualassignment.model;

import lombok.*;

import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@Table(name = "admin")
public class Admin extends  User{
    public Admin(String username, String email, String password){
        super(username, email, password);
    }
    public Admin(long id, String username, String email, String password){
        super(id, username, email, password);
    }
}

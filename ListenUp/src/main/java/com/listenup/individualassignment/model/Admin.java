package com.listenup.individualassignment.model;

import lombok.*;

@Data
public class Admin extends  User{
    public Admin(int id, String username, String email, String password){
        super(id, username, email, password);
    }
}

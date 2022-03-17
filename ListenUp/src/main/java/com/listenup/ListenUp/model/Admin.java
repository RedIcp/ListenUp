package com.listenup.ListenUp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin extends  User{
    public Admin(int id, String userName, String email, String password){
        super(id, userName, email, password);
    }
}

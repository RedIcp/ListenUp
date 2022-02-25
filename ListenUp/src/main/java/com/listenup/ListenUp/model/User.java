package com.listenup.ListenUp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User{
    private int id;
    private String userName;
    private String email;
    private String password;

    public User(int id, String userName, String email, String password){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Student{" +
                "ID: " + id +
                ", Username: " + userName + '\'' +
                ", Email: " + email + '\'' +
                ", Password: " + password +
                '}';
    }
}

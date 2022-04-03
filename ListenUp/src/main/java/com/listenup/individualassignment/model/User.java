package com.listenup.individualassignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class User{
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "ID: " + id +
                ", Username: " + username + '\'' +
                ", Email: " + email + '\'' +
                ", Password: " + password +
                '}';
    }
}

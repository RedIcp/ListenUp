package com.listenup.individualassignment.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public abstract class User{
    private int id;
    private String username;
    private String email;
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

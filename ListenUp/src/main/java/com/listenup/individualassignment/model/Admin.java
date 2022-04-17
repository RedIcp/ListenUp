package com.listenup.individualassignment.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Entity
@Table(name = "admin")
@Data
@AllArgsConstructor
public class Admin extends  User{
    public Admin(int id, String username, String email, String password){
        super(id, username, email, password);
    }
}

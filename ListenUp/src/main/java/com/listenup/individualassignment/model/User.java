package com.listenup.individualassignment.model;

import lombok.*;
import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "email")
    @Email
    private String email;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "password")
    private String password;

    protected User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

package com.listenup.individualassignment.entity;

import lombok.*;
import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

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
    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<UserRole> userRoles;

    protected User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    protected User(long id, String username, String email, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

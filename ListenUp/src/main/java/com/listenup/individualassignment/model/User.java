package com.listenup.individualassignment.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    @Id
    @Column(name = "id")
    private long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "password")
    private String password;
}

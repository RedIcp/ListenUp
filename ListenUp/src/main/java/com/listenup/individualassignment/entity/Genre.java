package com.listenup.individualassignment.entity;

import lombok.*;
import java.util.List;
import javax.persistence.*;

import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genre")
public class Genre {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<Song> songs;
}

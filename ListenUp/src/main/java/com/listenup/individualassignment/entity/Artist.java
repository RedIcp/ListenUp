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
@Table(name = "artist")
public class Artist {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @Length(min = 2, max = 50)
    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Song> songs;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Album> albums;
}

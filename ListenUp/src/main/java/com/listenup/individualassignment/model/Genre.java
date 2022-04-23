package com.listenup.individualassignment.model;

import lombok.*;
import java.util.List;
import java.util.ArrayList;
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
    private long id;

    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinTable(
            name = "song",
            joinColumns = @JoinColumn(name = "genre_id"))
    private List<Song> songs;

    public Genre(long id, String name){
        this.id = id;
        this.name = name;

        this.songs = new ArrayList<>();
    }
}

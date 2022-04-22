package com.listenup.individualassignment.model;

import lombok.*;
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;
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
    private long id;

    @Column(name = "name")
    @Length(min = 2, max = 50)
    private String name;

    @OneToMany
    @JoinTable(
            name = "song",
            joinColumns = @JoinColumn(name = "artist_id"))
    private List<Song> songs;

    @OneToMany
    @JoinTable(
            name = "album",
            joinColumns = @JoinColumn(name = "artist_id"))
    private List<Album> albums;

    public Artist(long id, String name){
        this.id = id;
        this.name = name;

        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
    }
}

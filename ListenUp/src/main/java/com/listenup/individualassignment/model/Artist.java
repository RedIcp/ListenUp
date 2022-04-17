package com.listenup.individualassignment.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Table(name = "artist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @Column(name = "id")
    private int id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "name")
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

    public Artist(int id, String name){
        this.id = id;
        this.name = name;

        this.songs = new ArrayList<>();
        this.albums = new ArrayList<>();
    }
}

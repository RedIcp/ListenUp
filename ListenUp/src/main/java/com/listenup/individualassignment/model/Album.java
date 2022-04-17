package com.listenup.individualassignment.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Table(name = "album")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @Id
    @Column(name = "id")
    private int id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany
    @JoinTable(
            name = "album_song",
            joinColumns = @JoinColumn(name = "album_id"))
    private List<AlbumSong> album_songs;

    @Column(name = "released_date")
    private Date released_date;

    @Column(name = "uploaded_date")
    private Date uploaded_date;

    public Album(int id, String name, Artist artist, Date released_date, Date uploaded_date){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.released_date = released_date;
        this.uploaded_date = uploaded_date;

        this.album_songs = new ArrayList<>();
    }
}

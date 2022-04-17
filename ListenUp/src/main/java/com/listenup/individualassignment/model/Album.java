package com.listenup.individualassignment.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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
    private long id;

    @NotNull
    @Length(min = 1, max = 50)
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
    private List<AlbumSong> albumSongs;

    @Column(name = "released_date")
    private Date releasedDate;

    @Column(name = "uploaded_date")
    private Date uploadedDate;

    public Album(long id, String name, Artist artist, Date releasedDate, Date uploadedDate){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.releasedDate = releasedDate;
        this.uploadedDate = uploadedDate;

        this.albumSongs = new ArrayList<>();
    }
}

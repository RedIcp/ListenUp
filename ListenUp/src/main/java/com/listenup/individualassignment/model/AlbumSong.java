package com.listenup.individualassignment.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Table(name = "album_song")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumSong extends Song{
    @NotNull
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    public AlbumSong(int id, String name, Genre genre, Album album){
        super(id, name, album.getArtist(), genre, album.getReleased_date(), album.getUploaded_date());
        this.album = album;
    }
}

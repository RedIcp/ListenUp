package com.listenup.individualassignment.model;

import lombok.*;
import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album_song")
public class AlbumSong extends Song{
    @NotNull
    @ManyToOne
    @JoinColumn(name = "album_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Album album;

    public AlbumSong(String name, Genre genre, Album album){
        super(name, album.getArtist(), genre, album.getReleasedDate(), album.getUploadedDate());
        this.album = album;
    }
    public AlbumSong(long id, String name, Genre genre, Album album){
        super(id, name, album.getArtist(), genre, album.getReleasedDate(), album.getUploadedDate());
        this.album = album;
    }
}


package com.listenup.ListenUp.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumSong extends Song{
    private Album album;

    public AlbumSong(int id, String name, Genre genre, Album album){
        super(id, name, genre);
        this.album = album;
    }
}

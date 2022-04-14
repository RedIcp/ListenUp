package com.listenup.individualassignment.model;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumSong extends Song{
    private Album album;

    public AlbumSong(int id, String name, Genre genre, Album album){
        super(id, name, genre);
        this.album = album;
    }
}

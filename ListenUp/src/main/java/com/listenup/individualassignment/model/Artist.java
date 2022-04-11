package com.listenup.individualassignment.model;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private int id;
    private String name;
    private List<Song> songs;
    private List<Album> albums;

    public Artist(int id, String name){
        this.id = id;
        this.name = name;

        songs = new ArrayList<>();
        albums = new ArrayList<>();
    }
}

package com.listenup.ListenUp.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genre {
    private int id;
    private String name;
    private List<Song> songs;

    public Genre(int id, String name){
        this.id = id;
        this.name = name;

        songs = new ArrayList<>();
    }
}

package com.listenup.individualassignment.model;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

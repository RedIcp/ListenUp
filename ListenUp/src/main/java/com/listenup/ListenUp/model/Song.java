package com.listenup.ListenUp.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Song{
    private int id;
    private String name;
    private Genre genre;
    private List<Artist> features;
    private List<User> viewers;
    private List<User> likedUsers;

    public Song(int id, String name, Genre genre){
        this.id = id;
        this.name = name;
        this.genre = genre;

        features = new ArrayList<>();
        viewers = new ArrayList<>();
        likedUsers = new ArrayList<>();
    }


}

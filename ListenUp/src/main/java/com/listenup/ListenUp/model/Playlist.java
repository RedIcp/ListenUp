package com.listenup.ListenUp.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Playlist {
    private int id;
    private String name;
    private boolean isPublic;
    private Customer owner;
    private List<Customer> likedUser;
    private List<Song> songs;

    public Playlist(int id, String name, Customer owner){
        this.id = id;
        this.name = name;
        this.owner = owner;

        likedUser = new ArrayList<>();
        songs = new ArrayList<>();
    }


}

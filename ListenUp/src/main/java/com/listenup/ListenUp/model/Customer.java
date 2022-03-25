package com.listenup.ListenUp.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer extends User{
    private List<Song> likedSongs;
    private List<Playlist> playlists;
    private List<Playlist> likedPlaylists;

    public Customer(int id, String username, String email, String password){
        super(id, username, email, password);

        likedSongs = new ArrayList<>();
        playlists = new ArrayList<>();
        likedPlaylists = new ArrayList<>();
    }
}

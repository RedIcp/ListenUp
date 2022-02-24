package com.listenup.ListenUp.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<Song> likedSongs;
    private List<Playlist> playlists;
    private List<Playlist> likedPlaylists;

    public Customer(int id, String userName, String email, String password){
        super(id, userName, email, password);

        likedSongs = new ArrayList<>();
        playlists = new ArrayList<>();
        likedPlaylists = new ArrayList<>();
    }
}

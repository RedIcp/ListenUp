package com.listenup.ListenUp.persistence.imp;

import com.listenup.ListenUp.model.Playlist;
import com.listenup.ListenUp.persistence.DBPlaylist;

import java.util.ArrayList;
import java.util.List;

public class DBPlaylistImp implements DBPlaylist {
    private List<Playlist> playlists;

    public DBPlaylistImp(){
        playlists = new ArrayList<>();
    }

    public boolean addPlaylist(int id, String name, boolean isPublic){
        return true;
    }
    public List<Playlist> getPlaylists(){
        return playlists;
    }
    public boolean editPlaylist(int id, String name, boolean isPublic){
        return true;
    }
    public boolean deletePlaylist(int id){
        return true;
    }
}

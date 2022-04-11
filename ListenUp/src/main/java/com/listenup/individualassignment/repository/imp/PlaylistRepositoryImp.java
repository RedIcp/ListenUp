package com.listenup.individualassignment.repository.imp;

import com.listenup.individualassignment.model.Playlist;
import com.listenup.individualassignment.repository.PlaylistRepository;

import java.util.ArrayList;
import java.util.List;

public class PlaylistRepositoryImp implements PlaylistRepository {
    private List<Playlist> playlists;

    public PlaylistRepositoryImp(){
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

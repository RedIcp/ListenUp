package com.listenup.individualassignment.repository.imp;

import com.listenup.individualassignment.model.Playlist;
import com.listenup.individualassignment.repository.PlaylistRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class PlaylistRepositoryImp implements PlaylistRepository {
    private final List<Playlist> playlists;

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

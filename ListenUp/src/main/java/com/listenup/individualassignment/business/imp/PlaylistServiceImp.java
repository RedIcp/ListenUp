package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.model.Playlist;
import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.repository.PlaylistRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class PlaylistServiceImp implements PlaylistService {
    private final PlaylistRepository db;

    public boolean addPlaylist(Playlist playlist){
        boolean result = false;
        if(!playlistExist(playlist.getId())){
            getPlaylists().add(playlist);
            db.save(playlist);
            result = true;
        }
        return result;
    }
    public List<Playlist> getPlaylists(){
        return db.findAll();
    }
    public boolean editPlaylist(Playlist playlist){
        boolean result = false;
        Playlist old = getPlaylist(playlist.getId());
        if(old != null){
            old.setName(playlist.getName());
            db.save(playlist);
            result = true;
        }
        return result;
    }
    public boolean deletePlaylist(long id){
        boolean result = false;
        if(playlistExist(id)){
            getPlaylists().remove(getPlaylist(id));
            db.delete(getPlaylist(id));
            result = true;
        }
        return result;
    }

    public Playlist getPlaylist(long id){
        for (Playlist playlist : getPlaylists()){
            if(playlist.getId() == id){
                return playlist;
            }
        }
        return null;
    }
    public boolean playlistExist(long id){
        boolean result = true;
        if(getPlaylist(id)==null){
            result = false;
        }
        return result;
    }
}

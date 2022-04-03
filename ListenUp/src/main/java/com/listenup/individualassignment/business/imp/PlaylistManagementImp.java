package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.PlaylistManagement;
import com.listenup.individualassignment.model.Playlist;
import com.listenup.individualassignment.persistence.DBPlaylist;

import java.util.List;

public class PlaylistManagementImp implements PlaylistManagement {
    private DBPlaylist db;

    public PlaylistManagementImp(DBPlaylist db){
        this.db = db;
    }

    public boolean addPlaylist(Playlist playlist){
        boolean result = false;
        if(!playlistExist(playlist.getId())){
            getPlaylists().add(playlist);
            db.addPlaylist(playlist.getId(), playlist.getName(), playlist.isPublic());
            result = true;
        }
        return result;
    }
    public List<Playlist> getPlaylists(){
        return db.getPlaylists();
    }
    public boolean editPlaylist(Playlist playlist){
        boolean result = false;
        Playlist old = getPlaylist(playlist.getId());
        if(old != null){
            old.setName(playlist.getName());
            db.editPlaylist(playlist.getId(), playlist.getName(), playlist.isPublic());
            result = true;
        }
        return result;
    }
    public boolean deletePlaylist(int id){
        boolean result = false;
        if(playlistExist(id)){
            getPlaylists().remove(getPlaylist(id));
            db.deletePlaylist(id);
            result = true;
        }
        return result;
    }

    public Playlist getPlaylist(int id){
        for (Playlist playlist : getPlaylists()){
            if(playlist.getId() == id){
                return playlist;
            }
        }
        return null;
    }
    public boolean playlistExist(int id){
        boolean result = true;
        if(getPlaylist(id)==null){
            result = false;
        }
        return result;
    }
}

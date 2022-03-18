package com.listenup.ListenUp.business.imp;

import com.listenup.ListenUp.business.PlaylistManagement;
import com.listenup.ListenUp.model.Playlist;
import com.listenup.ListenUp.persistence.DBPlaylist;

import java.util.List;

public class PlaylistManagementImp implements PlaylistManagement {
    private DBPlaylist db;

    public PlaylistManagementImp(DBPlaylist db){
        this.db = db;
    }

    public boolean addPlaylist(Playlist playlist){
        if(playlistExist(playlist.getId()) == false){
            getPlaylists().add(playlist);
            db.addPlaylist(playlist.getId(), playlist.getName(), playlist.isPublic());
            return true;
        }
        return false;
    }
    public List<Playlist> getPlaylists(){
        return db.getPlaylists();
    }
    public boolean editPlaylist(Playlist playlist){
        Playlist old = getPlaylist(playlist.getId());
        if(old == null){
            return false;
        }
        old.setName(playlist.getName());
        db.editPlaylist(playlist.getId(), playlist.getName(), playlist.isPublic());
        return true;
    }
    public boolean deletePlaylist(int id){
        if(playlistExist(id) == true){
            getPlaylists().remove(getPlaylist(id));
            db.deletePlaylist(id);
            return true;
        }
        return false;
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
        if(getPlaylist(id)!=null){
            return true;
        }
        return false;
    }
}

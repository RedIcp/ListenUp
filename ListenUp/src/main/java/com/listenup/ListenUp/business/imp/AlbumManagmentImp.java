package com.listenup.ListenUp.business.imp;

import com.listenup.ListenUp.business.AlbumManagment;
import com.listenup.ListenUp.model.Album;
import com.listenup.ListenUp.persistence.DBAlbum;
import com.listenup.ListenUp.persistence.imp.DBAlbumImp;

import java.util.List;

public class AlbumManagmentImp implements AlbumManagment {
    private DBAlbum db;

    public AlbumManagmentImp(DBAlbum db){
        this.db = db;
    }

    public boolean addAlbum(Album album){
        if(albumExist(album.getId()) == false){
            getAlbum().add(album);
            return true;
        }
        return false;
    }
    public List<Album> getAlbum(){
        return db.getAlbums();
    }
    public boolean editAlbum(Album album){
        Album old = getAlbum(album.getId());
        if(old == null){
            return false;
        }
        return true;
    }
    public boolean deleteAlbum(int id){
        return true;
    }
    public Album getAlbum(int id){
        for(Album album: getAlbum()){
            if(album.getId()==id){
                return album;
            }
        }
        return null;
    }
    private boolean albumExist(int id){
        if(getAlbum(id)!= null){
            return true;
        }
        return false;
    }

}

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
            getAlbums().add(album);
            return true;
        }
        return false;
    }
    public List<Album> getAlbums(){
        return db.getAlbums();
    }
    public boolean editAlbum(Album album){
        Album old = getAlbum(album.getId());
        if(old == null){
            return false;
        }
        old.setName(album.getName());
        old.setReleasedDate(album.getReleasedDate());
        old.setUploadedDate(album.getUploadedDate());
        old.setArtist(album.getArtist());
        return true;
    }
    public boolean deleteAlbum(int id){
        if(albumExist(id) == true){
            getAlbums().remove(getAlbum(id));
            return true;
        }
        return false;
    }
    public Album getAlbum(int id){
        for(Album album: getAlbums()){
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

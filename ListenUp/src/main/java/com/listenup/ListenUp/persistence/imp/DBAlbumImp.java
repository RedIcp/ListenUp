package com.listenup.ListenUp.persistence.imp;

import com.listenup.ListenUp.model.Album;
import com.listenup.ListenUp.model.Artist;
import com.listenup.ListenUp.persistence.DBAlbum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBAlbumImp implements DBAlbum {
    private List<Album> albums;

    public DBAlbumImp(){
        albums = new ArrayList<>();
    }

    public boolean addAlbum(int id, String name, Artist artist, Date releasedDate, Date uploadedDate){
        return true;
    }
    public List<Album> getAlbums(){
        return albums;
    }
    public boolean editAlbum(int id, String name, Artist artist, Date releasedDate, Date uploadedDate){
        return  true;
    }
    public boolean deleteAlbum(int id){
        return true;
    }
}

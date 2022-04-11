package com.listenup.individualassignment.repository.imp;

import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.repository.AlbumRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlbumRepositoryImp implements AlbumRepository {
    private List<Album> albums;

    public AlbumRepositoryImp(){
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

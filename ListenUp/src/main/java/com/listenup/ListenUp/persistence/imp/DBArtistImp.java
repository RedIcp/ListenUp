package com.listenup.ListenUp.persistence.imp;

import com.listenup.ListenUp.model.Artist;
import com.listenup.ListenUp.persistence.DBArtist;

import java.util.ArrayList;
import java.util.List;

public class DBArtistImp implements DBArtist {
    private List<Artist> artists;

    public DBArtistImp(){
        artists = new ArrayList<>();
    }
    public boolean addArtist(int id, String name){
        return true;
    }
    public List<Artist> getArtists(){
        return artists;
    }
    public boolean editArtist(int id, String name){
        return true;
    }
    public boolean deleteArtist(int id){
        return true;
    }
}

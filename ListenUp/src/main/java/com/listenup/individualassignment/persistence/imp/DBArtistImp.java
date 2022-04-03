package com.listenup.individualassignment.persistence.imp;

import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.persistence.DBArtist;

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

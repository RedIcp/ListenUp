package com.listenup.individualassignment.repository.imp;

import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.repository.ArtistRepository;

import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryImp implements ArtistRepository {
    private List<Artist> artists;

    public ArtistRepositoryImp(){
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

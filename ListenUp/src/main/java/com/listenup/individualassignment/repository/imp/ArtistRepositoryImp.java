package com.listenup.individualassignment.repository.imp;

import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.repository.ArtistRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class ArtistRepositoryImp implements ArtistRepository {
    private final List<Artist> artists;

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

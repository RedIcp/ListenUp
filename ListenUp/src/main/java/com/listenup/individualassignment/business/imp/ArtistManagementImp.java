package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.ArtistManagement;
import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.repository.ArtistRepository;

import java.util.List;

public class ArtistManagementImp implements ArtistManagement {
    private ArtistRepository db;

    public ArtistManagementImp(ArtistRepository db){
        this.db = db;
    }

    public boolean addArtist(Artist artist){
        boolean result = false;
        if(!artistExit(artist.getId())){
            getArtists().add(artist);
            db.addArtist(artist.getId(), artist.getName());
            result = true;
        }
        return result;
    }

    public List<Artist> getArtists(){
        return db.getArtists();
    }

    public boolean editArtist(Artist artist){
        boolean result = false;
        Artist old = getArtist(artist.getId());
        if(old != null){
            old.setName(artist.getName());
            db.editArtist(artist.getId(), artist.getName());
            result =  true;
        }
        return result;
    }

    public boolean deleteArtist(int id){
        boolean result = false;
        if(artistExit(id)){
            getArtists().remove(getArtist(id));
            db.deleteArtist(id);
            result = true;
        }
        return result;
    }

    public Artist getArtist(int id){
        for(Artist artist : getArtists()){
            if(artist.getId() == id){
                return artist;
            }
        }
        return null;
    }
    private boolean artistExit(int id){
        boolean result = true;
        if(getArtist(id) == null){
            result = false;
        }
        return result;
    }
}

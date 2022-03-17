package com.listenup.ListenUp.business.imp;

import com.listenup.ListenUp.model.Artist;
import com.listenup.ListenUp.persistence.DBArtist;

import java.util.List;

public class ArtistManagmentImp {
    private DBArtist db;

    public ArtistManagmentImp(DBArtist db){
        this.db = db;
    }
    public boolean addArtist(Artist artist){
        if(artistExit(artist.getId()) == false){
            getArtists().add(artist);
            return true;
        }
        return false;
    }
    public List<Artist> getArtists(){
        return db.getArtists();
    }
    public boolean editArtist(Artist artist){
        return true;
    }
    public boolean deleteArtist(int id){
        return true;
    }
    public Artist getArtist(int id){
        for(Artist artist : getArtists()){
            if(artist.getId() == id){
                return artist;
            }
        }
        return null;
    }
    public boolean artistExit(int id){
        if(getArtist(id) != null){
            return true;
        }
        return false;
    }
}

package com.listenup.ListenUp.business.imp;

import com.listenup.ListenUp.business.ArtistManagement;
import com.listenup.ListenUp.model.Artist;
import com.listenup.ListenUp.persistence.DBArtist;

import java.util.List;

public class ArtistManagementImp implements ArtistManagement {
    private DBArtist db;

    public ArtistManagementImp(DBArtist db){
        this.db = db;
    }

    public boolean addArtist(Artist artist){
        if(artistExit(artist.getId()) == false){
            getArtists().add(artist);
            db.addArtist(artist.getId(), artist.getName());
            return true;
        }
        return false;
    }

    public List<Artist> getArtists(){
        return db.getArtists();
    }

    public boolean editArtist(Artist artist){
        Artist old = getArtist(artist.getId());
        if(old == null){
            return false;
        }
        old.setName(artist.getName());
        db.editArtist(artist.getId(), artist.getName());
        return true;
    }

    public boolean deleteArtist(int id){
        if(artistExit(id) == true){
            getArtists().remove(getArtist(id));
            db.deleteArtist(id);
            return true;
        }
        return false;
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
        if(getArtist(id) != null){
            return true;
        }
        return false;
    }
}

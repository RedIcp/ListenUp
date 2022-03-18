package com.listenup.ListenUp.business.imp;

import com.listenup.ListenUp.business.SongManagement;
import com.listenup.ListenUp.model.Song;
import com.listenup.ListenUp.persistence.DBSong;

import java.util.List;

public class SongManagementImp implements SongManagement {
    private DBSong db;

    public SongManagementImp(DBSong db){
        this.db = db;
    }

    public boolean addSong(Song song){
        if(songExist(song.getId()) == false){
            getSongs().add(song);
            db.addSong(song.getId(), song.getName(), song.getGenre());
            return true;
        }
        return false;
    }

    public List<Song> getSongs(){
        return db.getSongs();
    }

    public boolean editSong(Song song){
        Song old = getSong(song.getId());
        if(old == null){
            return false;
        }
        old.setName(song.getName());
        old.setGenre(song.getGenre());
        db.editSong(song.getId(), song.getName(), song.getGenre());
        return true;
    }

    public boolean deleteSong(int id){
        if(songExist(id) == false){
            getSongs().remove(getSong(id));
            db.deleteSong(id);
            return true;
        }
        return false;
    }

    public Song getSong(int id){
        for(Song song: getSongs()){
            if(song.getId() == id){
                return song;
            }
        }
        return null;
    }
    public boolean songExist(int id){
        if(getSong(id)!=null){
            return true;
        }
        return false;
    }
}

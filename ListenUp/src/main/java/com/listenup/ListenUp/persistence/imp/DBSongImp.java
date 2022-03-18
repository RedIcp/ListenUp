package com.listenup.ListenUp.persistence.imp;

import com.listenup.ListenUp.model.Genre;
import com.listenup.ListenUp.model.Song;
import com.listenup.ListenUp.persistence.DBSong;

import java.util.ArrayList;
import java.util.List;

public class DBSongImp implements DBSong {
    private List<Song> songs;

    public DBSongImp(){
        songs = new ArrayList<>();
    }
    public boolean addSong(int id, String name, Genre genre){
        return true;
    }
    public List<Song> getSongs(){
        return songs;
    }
    public boolean editSong(int id, String name, Genre genre){
        return true;
    }
    public boolean deleteSong(int id){
        return true;
    }
}

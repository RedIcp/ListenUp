package com.listenup.individualassignment.persistence.imp;

import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.persistence.DBSong;

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

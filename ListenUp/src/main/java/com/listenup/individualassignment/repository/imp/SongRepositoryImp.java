package com.listenup.individualassignment.repository.imp;

import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.repository.SongRepository;

import java.util.ArrayList;
import java.util.List;

public class SongRepositoryImp implements SongRepository {
    private List<Song> songs;

    public SongRepositoryImp(){
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

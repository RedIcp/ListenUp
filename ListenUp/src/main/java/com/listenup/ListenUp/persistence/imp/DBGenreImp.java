package com.listenup.ListenUp.persistence.imp;

import com.listenup.ListenUp.model.Genre;
import com.listenup.ListenUp.persistence.DBGenre;

import java.util.ArrayList;
import java.util.List;

public class DBGenreImp implements DBGenre {
    private List<Genre> genres;

    public DBGenreImp(){
        genres = new ArrayList<>();
    }

    public boolean addGenre(int id, String name){
        return true;
    }
    public List<Genre> getGenres(){
        return genres;
    }
    public boolean editGenre(int id, String name){
        return true;
    }
    public boolean deleteGenre(int id){
        return true;
    }
}

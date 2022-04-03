package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.GenreManagement;
import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.persistence.DBGenre;

import java.util.List;

public class GenreManagementImp implements GenreManagement {
    private DBGenre db;

    public GenreManagementImp(DBGenre db){
        this.db = db;
    }

    public boolean addGenre(Genre genre){
        boolean result = false;
        if(!genreExist(genre.getId())){
            getGenres().add(genre);
            db.addGenre(genre.getId(), genre.getName());
            result = true;
        }
        return result;
    }

    public List<Genre> getGenres(){
        return  db.getGenres();
    }

    public boolean editGenre(Genre genre){
        boolean result = false;
        Genre old = getGenre(genre.getId());
        if(old != null){
            old.setName(genre.getName());
            db.editGenre(genre.getId(), genre.getName());
            result = true;
        }
        return result;
    }

    public boolean deleteGenre(int id){
        boolean result = false;
        if(genreExist(id)){
            getGenres().remove(getGenre(id));
            db.deleteGenre(id);
            result = true;
        }
        return result;
    }

    public Genre getGenre(int id){
        for(Genre genre : getGenres()){
            if(genre.getId() == id){
                return genre;
            }
        }
        return null;
    }
    public boolean genreExist(int id){
        boolean result = true;
        if(getGenre(id) == null){
            result = false;
        }
        return result;
    }
}

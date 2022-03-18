package com.listenup.ListenUp.business.imp;

import com.listenup.ListenUp.business.GenreManagement;
import com.listenup.ListenUp.model.Genre;
import com.listenup.ListenUp.persistence.DBGenre;

import java.util.List;

public class GenreManagementImp implements GenreManagement {
    private DBGenre db;

    public GenreManagementImp(DBGenre db){
        this.db = db;
    }

    public boolean addGenre(Genre genre){
        if(genreExist(genre.getId()) == false){
            getGenres().add(genre);
            db.addGenre(genre.getId(), genre.getName());
            return true;
        }
        return false;
    }

    public List<Genre> getGenres(){
        return  db.getGenres();
    }

    public boolean editGenre(Genre genre){
        Genre old = getGenre(genre.getId());
        if(old == null){
            return false;
        }
        old.setName(genre.getName());
        db.editGenre(genre.getId(), genre.getName());
        return true;
    }

    public boolean deleteGenre(int id){
        if(genreExist(id) == true){
            getGenres().remove(getGenre(id));
            db.deleteGenre(id);
            return true;
        }
        return false;
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
        if(getGenre(id) != null){
            return true;
        }
        return false;
    }
}

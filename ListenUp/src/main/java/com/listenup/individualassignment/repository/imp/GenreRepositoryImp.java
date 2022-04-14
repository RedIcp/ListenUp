package com.listenup.individualassignment.repository.imp;

import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.repository.GenreRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class GenreRepositoryImp implements GenreRepository {
    private final List<Genre> genres;

    public GenreRepositoryImp(){
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

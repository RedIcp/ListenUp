package com.listenup.individualassignment.business;

import com.listenup.individualassignment.model.Genre;

import java.util.List;

public interface GenreManagement {
    boolean addGenre(Genre genre);
    List<Genre> getGenres();
    boolean editGenre(Genre genre);
    boolean deleteGenre(int id);
    Genre getGenre(int id);
}

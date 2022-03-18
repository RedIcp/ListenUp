package com.listenup.ListenUp.business;

import com.listenup.ListenUp.model.Genre;

import java.util.List;

public interface GenreManagement {
    boolean addGenre(Genre genre);
    List<Genre> getGenres();
    boolean editGenre(Genre genre);
    boolean deleteGenre(int id);
    Genre getGenre(int id);
}

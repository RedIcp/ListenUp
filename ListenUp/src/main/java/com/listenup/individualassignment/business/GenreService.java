package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.Genre;

public interface GenreService {
    boolean addGenre(Genre genre);
    List<Genre> getGenres();
    Genre getGenre(long id);
    boolean editGenre(Genre genre);
    boolean deleteGenre(long id);
}

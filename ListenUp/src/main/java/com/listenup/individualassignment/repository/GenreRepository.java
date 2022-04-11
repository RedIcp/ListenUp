package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.Genre;

import java.util.List;

public interface GenreRepository {
    boolean addGenre(int id, String name);
    List<Genre> getGenres();
    boolean editGenre(int id, String name);
    boolean deleteGenre(int id);
}

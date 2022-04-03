package com.listenup.individualassignment.persistence;

import com.listenup.individualassignment.model.Genre;

import java.util.List;

public interface DBGenre {
    boolean addGenre(int id, String name);
    List<Genre> getGenres();
    boolean editGenre(int id, String name);
    boolean deleteGenre(int id);
}

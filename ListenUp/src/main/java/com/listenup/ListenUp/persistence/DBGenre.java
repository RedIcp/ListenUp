package com.listenup.ListenUp.persistence;

import com.listenup.ListenUp.model.Genre;

import java.util.List;

public interface DBGenre {
    boolean addGenre(int id, String name);
    List<Genre> getGenres();
    boolean editGenre(int id, String name);
    boolean deleteGenre(int id);
}

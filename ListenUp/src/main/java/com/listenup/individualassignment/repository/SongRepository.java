package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.model.Song;

import java.util.List;

public interface SongRepository {
    boolean addSong(int id, String name, Genre genre);
    List<Song> getSongs();
    boolean editSong(int id, String name, Genre genre);
    boolean deleteSong(int id);
}

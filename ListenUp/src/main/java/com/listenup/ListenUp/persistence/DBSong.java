package com.listenup.ListenUp.persistence;

import com.listenup.ListenUp.model.Genre;
import com.listenup.ListenUp.model.Song;

import java.util.List;

public interface DBSong {
    boolean addSong(int id, String name, Genre genre);
    List<Song> getSongs();
    boolean editSong(int id, String name, Genre genre);
    boolean deleteSong(int id);
}

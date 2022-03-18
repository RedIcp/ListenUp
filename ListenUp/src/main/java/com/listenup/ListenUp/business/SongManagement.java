package com.listenup.ListenUp.business;

import com.listenup.ListenUp.model.Genre;
import com.listenup.ListenUp.model.Song;

import java.util.List;

public interface SongManagement {
    boolean addSong(Song song);
    List<Song> getSongs();
    boolean editSong(Song song);
    boolean deleteSong(int id);
    Song getSong(int id);
}

package com.listenup.individualassignment.business;

import com.listenup.individualassignment.model.Song;

import java.util.List;

public interface SongService {
    boolean addSong(Song song);
    List<Song> getSongs();
    boolean editSong(Song song);
    boolean deleteSong(int id);
    Song getSong(int id);
}

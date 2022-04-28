package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.AlbumSong;
import com.listenup.individualassignment.model.SingleSong;
import com.listenup.individualassignment.model.Song;

public interface SongService {
    boolean addSingleSong(SingleSong song);
    boolean addAlbumSong(AlbumSong song);
    List<Song> getSongs();
    Song getSong(long id);
    boolean editSong(Song song);
    boolean deleteSong(long id);
}

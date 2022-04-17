package com.listenup.individualassignment.business;

import com.listenup.individualassignment.dto.SongDTO;
import com.listenup.individualassignment.model.Song;

import java.util.List;

public interface SongService {
    Song songDTOConvertor(SongDTO dto);
    boolean addSong(Song song);
    List<Song> getSongs();
    boolean editSong(Song song);
    boolean deleteSong(long id);
    Song getSong(long id);
}

package com.listenup.individualassignment.business;

import java.util.List;
import com.listenup.individualassignment.model.Song;
import com.listenup.individualassignment.dto.CreateUpdate.SongDTO;

public interface SongService {
    //dto
    Song songDTOConvertor(SongDTO dto);

    //crud
    boolean addSong(Song song);
    List<Song> getSongs();
    Song getSong(long id);
    boolean editSong(Song song);
    boolean deleteSong(long id);
}

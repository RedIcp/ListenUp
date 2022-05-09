package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.createdto.CreateAlbumSongDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;

public interface SongService {
    CreateSingleSongDTO addSingleSong(CreateSingleSongDTO song);
    CreateAlbumSongDTO addAlbumSong(CreateAlbumSongDTO song);
    List<SingleSongDTO> getSongs();
    SingleSongDTO getSong(long id);
    SingleSongDTO editSong(SingleSongDTO song);
    boolean deleteSong(long id);
}

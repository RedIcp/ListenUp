package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongResponseDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;

public interface SongService {
    CreateSingleSongResponseDTO addSingleSong(CreateSingleSongRequestDTO song);
    CreateAlbumSongResponseDTO addAlbumSong(CreateAlbumSongRequestDTO song);
    List<SingleSongDTO> getSongs();
    SingleSongDTO getSong(long id);
    SingleSongDTO editSong(SingleSongDTO song);
    boolean deleteSong(long id);
}

package com.listenup.individualassignment.business.song;

import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongResponseDTO;

public interface CreateAlbumSongUseCase {
    CreateAlbumSongResponseDTO addAlbumSong(CreateAlbumSongRequestDTO song);
}

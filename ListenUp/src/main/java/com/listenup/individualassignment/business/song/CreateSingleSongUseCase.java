package com.listenup.individualassignment.business.song;

import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongResponseDTO;

public interface CreateSingleSongUseCase {
    CreateSingleSongResponseDTO addSingleSong(CreateSingleSongRequestDTO song);
}

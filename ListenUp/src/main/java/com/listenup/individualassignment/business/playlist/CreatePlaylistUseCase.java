package com.listenup.individualassignment.business.playlist;

import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;

public interface CreatePlaylistUseCase {
    CreatePlaylistResponseDTO addPlaylist(CreatePlaylistRequestDTO playlist);
}

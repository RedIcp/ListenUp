package com.listenup.individualassignment.business.album;

import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumResponseDTO;

public interface CreateAlbumUseCase {
    CreateAlbumResponseDTO addAlbum(CreateAlbumRequestDTO album);
}

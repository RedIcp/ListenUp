package com.listenup.individualassignment.business.artist;

import com.listenup.individualassignment.dto.createdto.CreateArtistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistResponseDTO;

public interface CreateArtistUseCase {
    CreateArtistResponseDTO addArtist(CreateArtistRequestDTO artist);
}

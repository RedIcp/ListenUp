package com.listenup.individualassignment.business.genre;

import com.listenup.individualassignment.dto.createdto.CreateGenreRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreResponseDTO;

public interface CreateGenreUseCase {
    CreateGenreResponseDTO addGenre(CreateGenreRequestDTO genre);
}

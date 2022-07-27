package com.listenup.individualassignment.business.genre;

import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;

import java.util.List;

public interface GetGenresUseCase {
    List<GenreDTO> getGenres();
}

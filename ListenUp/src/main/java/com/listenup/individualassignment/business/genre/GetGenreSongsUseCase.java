package com.listenup.individualassignment.business.genre;

import com.listenup.individualassignment.dto.GenreSongListDTO;

public interface GetGenreSongsUseCase {
    GenreSongListDTO getGenreSongs(long id);
}

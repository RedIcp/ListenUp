package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;

public interface GenreService {
    CreateGenreDTO addGenre(CreateGenreDTO genre);
    List<GenreDTO> getGenres();
    GenreSongListDTO getGenreSongs(long id);
    GenreDTO editGenre(GenreDTO genre);
    boolean deleteGenre(long id);
}

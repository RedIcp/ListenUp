package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;

public interface GenreService {
    CreateGenreResponseDTO addGenre(CreateGenreRequestDTO genre);
    List<GenreDTO> getGenres();
    GenreSongListDTO getGenreSongs(long id);
    GenreDTO editGenre(GenreDTO genre);
    boolean deleteGenre(long id);
}

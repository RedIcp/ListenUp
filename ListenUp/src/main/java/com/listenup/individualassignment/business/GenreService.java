package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createupdate.GenreDTO;

public interface GenreService {
    //dto
    Genre genreDTOConvertor(GenreDTO dto);
    GenreSongListDTO genreObjConvertor(Genre genre);
    List<GenreDTO> getGenreDTOs(List<Genre> genres);

    //crud
    boolean addGenre(Genre genre);
    List<Genre> getGenres();
    Genre getGenre(long id);
    boolean editGenre(Genre genre);
    boolean deleteGenre(long id);
}

package com.listenup.individualassignment.business;

import com.listenup.individualassignment.dto.GenreDTO;
import com.listenup.individualassignment.model.Genre;

import java.util.List;

public interface GenreService {
    Genre genreDTOConvertor(GenreDTO dto);
    boolean addGenre(Genre genre);
    List<Genre> getGenres();
    boolean editGenre(Genre genre);
    boolean deleteGenre(long id);
    Genre getGenre(long id);
}

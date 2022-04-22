package com.listenup.individualassignment.business;

import java.util.List;
import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.dto.CreateUpdate.GenreDTO;

public interface GenreService {
    //dto
    Genre genreDTOConvertor(GenreDTO dto);

    //crud
    boolean addGenre(Genre genre);
    List<Genre> getGenres();
    Genre getGenre(long id);
    boolean editGenre(Genre genre);
    boolean deleteGenre(long id);
}

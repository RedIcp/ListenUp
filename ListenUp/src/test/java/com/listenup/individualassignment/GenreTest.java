package com.listenup.individualassignment;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.business.GenreService;
import com.listenup.individualassignment.dto.createupdate.GenreDTO;
import com.listenup.individualassignment.repository.GenreRepository;
import com.listenup.individualassignment.business.imp.GenreServiceImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GenreTest {
    @Autowired
    GenreRepository dbGenre;

    GenreService genreMG;

    @BeforeEach
    void  setUp(){
        genreMG = new GenreServiceImp(dbGenre);
    }
    //dto: valid input
    @Test
    void genreDTOConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);

        GenreDTO genreDTO= new GenreDTO(1, "Pop");
        assertNotNull(genreMG.genreDTOConvertor(genreDTO));
    }
    @Test
    void genreObjConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);

        assertNotNull(genreMG.genreObjConvertor(genre));
    }
    @Test
    void genreDTOListConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        List<Genre> genres = new ArrayList<>();
        genres.add(genre);

        assertNotNull(genreMG.getGenreDTOs(genres));
    }

    //create genre: valid input
    @Test
    void addGenreValidInput() {
        Genre genre = new Genre(1, "Pop");
        Genre genre1 = Genre.builder().id(2).name("Hip-hop").build();
        genreMG.addGenre(genre1);
        assertTrue(genreMG.addGenre(genre));
    }
    //create genre: same id
    @Test
    void addGenreSameID() {
        Genre genre1 = Genre.builder().id(1).name("Pop").build();
        Genre genre2 = Genre.builder().id(1).name("Hip-hop").build();
        genreMG.addGenre(genre1);
        assertFalse(genreMG.addGenre(genre2));
    }

    //update genre: valid input
    @Test
    void updateGenreValidInput() {
        Genre genre1 = new Genre(1, "Pop");
        Genre genre2 = new Genre(1, "Hip-Hop");
        genreMG.addGenre(genre1);
        assertTrue(genreMG.editGenre(genre2));
    }
    //update genre: genre not in database
    @Test
    void updateGenreInvalidID() {
        Genre genre = new Genre(1, "Hip-Hop");
        assertFalse(genreMG.editGenre(genre));
    }

    //delete genre: valid input
    @Test
    void deleteGenreValidInput() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        assertTrue(genreMG.deleteGenre(1));
    }

    //delete artist: invalid id
    @Test
    void deleteGenreInvalidID() {
        assertFalse(genreMG.deleteGenre(100));
    }
}

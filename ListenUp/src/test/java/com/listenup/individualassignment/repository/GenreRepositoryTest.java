package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.entity.Artist;
import com.listenup.individualassignment.entity.Genre;
import com.listenup.individualassignment.entity.SingleSong;
import com.listenup.individualassignment.entity.Song;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GenreRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private GenreRepository repository;

    Date date = new Date(2021,11,27);

    @Test
    void save(){
        Genre genre = Genre.builder()
                .name("Pop")
                .build();

        Genre actualGenre = repository.save(genre);

        Genre expectedGenre = Genre.builder()
                .id(actualGenre.getId())
                .name("Pop")
                .build();

        assertEquals(actualGenre, expectedGenre);
    }

    @Test
    void getGenreSongs(){
        Artist artist = Artist.builder()
                .name("Maroon 5")
                .build();
        entityManager.persist(artist);

        Genre genre = Genre.builder()
                .name("Pop")
                .build();
        entityManager.persist(genre);

        Song song = new SingleSong("Map", artist, genre, date, date);
        entityManager.persist(song);

        entityManager.flush();
        entityManager.clear();

        Genre savedGenre = entityManager.find(Genre.class, genre.getId());

        assertEquals(List.of(song).size(), savedGenre.getSongs().size());
    }

    @Test
    void getAllGenres() {
        Genre genre1 = Genre.builder()
                .name("Pop")
                .build();
        Genre genre2 = Genre.builder()
                .name("Hip-hop")
                .build();

        entityManager.persist(genre1);
        entityManager.persist(genre2);

        List<Genre> expectedList = new ArrayList<>();
        expectedList.add(genre1);
        expectedList.add(genre2);

        List<Genre> actualList = repository.findAll();

        assertEquals(actualList, expectedList);
    }
}
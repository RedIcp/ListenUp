package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.*;
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
class SongRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SongRepository repository;

    Date date = new Date(2021,11,27);

    Artist artist = Artist.builder()
            .name("Maroon 5")
            .build();

    Album album = Album.builder()
            .name("V")
            .artist(artist)
            .releasedDate(date)
            .uploadedDate(date)
            .build();

    Genre genre = Genre.builder()
            .name("Pop")
            .build();
    @Test
    void save(){
        entityManager.persist(artist);
        entityManager.persist(album);
        entityManager.persist(genre);

        Song song = new SingleSong("Sugar", artist, genre, date, date);

        Song actualSong = repository.save(song);

        Song expectedSong = new SingleSong(actualSong.getId(),"Sugar", artist, genre, date, date);

        assertEquals(actualSong.getName(), expectedSong.getName());
    }

    @Test
    void getAllSongs(){
        entityManager.persist(artist);
        entityManager.persist(album);
        entityManager.persist(genre);

        Song song = new SingleSong("Sugar", artist, genre, date, date);
        entityManager.persist(song);

        List<Song> expectedList = new ArrayList<>();
        expectedList.add(song);

        List<Song> actualList = repository.findAll();

        assertEquals(expectedList.size(), actualList.size());
    }
}
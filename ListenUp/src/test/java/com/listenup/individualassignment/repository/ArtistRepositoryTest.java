package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.repository.entity.Artist;
import com.listenup.individualassignment.repository.entity.Genre;
import com.listenup.individualassignment.repository.entity.SingleSong;
import com.listenup.individualassignment.repository.entity.Song;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ArtistRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ArtistRepository repository;

    final Date date = new Date(2021,11,27);

    @Test
    void save(){
        Artist artist = Artist.builder()
                .name("Maroon 5")
                .build();

        entityManager.persist(artist);

        Artist actualArtist = repository.save(artist);

        Artist expectedArtist = Artist.builder()
                .id(actualArtist.getId())
                .name("Maroon 5")
                .build();

        assertEquals(actualArtist, expectedArtist);
    }

    @Test
    void getArtistSongs(){
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

        Artist savedArtist = entityManager.find(Artist.class, artist.getId());

        assertEquals(List.of(song).size(), savedArtist.getSongs().size());
    }

    @Test
    void getAllArtists(){
        Artist artist1 = Artist.builder()
                .name("Maroon 5")
                .songs(Collections.emptyList())
                .albums(Collections.emptyList())
                .build();
        Artist artist2 = Artist.builder()
                .name("Post Malone")
                .songs(Collections.emptyList())
                .albums(Collections.emptyList())
                .build();

        entityManager.persist(artist1);
        entityManager.persist(artist2);

        List<Artist> expectedList = new ArrayList<>();
        expectedList.add(artist1);
        expectedList.add(artist2);

        List<Artist> actualList = repository.findAll();

        assertEquals(actualList, expectedList);
    }
}
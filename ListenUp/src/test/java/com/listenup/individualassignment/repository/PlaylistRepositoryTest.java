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
class PlaylistRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlaylistRepository repository;

    Date date = new Date(2021,11,27);

    Customer customer = new Customer("Yellow", "yellow@gmail.com", "123Yellow");

    @Test
    void save(){
        entityManager.persist(customer);

        Playlist playlist = Playlist.builder()
                .name("Chill")
                .customer(customer)
                .build();

        Playlist actualPlaylist = repository.save(playlist);

        Playlist expectedPlaylist = Playlist.builder()
                .id(actualPlaylist.getId())
                .name("Chill")
                .customer(customer)
                .build();

        assertEquals(actualPlaylist, expectedPlaylist);
    }

    @Test
    void getPlaylistSongs(){
        entityManager.persist(customer);

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

        Playlist playlist = Playlist.builder()
                .name("Chill")
                .customer(customer)
                .songs(List.of(song))
                .build();
        entityManager.persist(playlist);

        entityManager.flush();
        entityManager.clear();

        Playlist savedPlaylist = entityManager.find(Playlist.class, playlist.getId());

        assertEquals(List.of(song).size(), savedPlaylist.getSongs().size());
    }

    @Test
    void getAllPlaylists() {
        entityManager.persist(customer);

        Playlist playlist1 = Playlist.builder()
                .name("Chill")
                .customer(customer)
                .build();
        Playlist playlist2 = Playlist.builder()
                .name("Workout")
                .customer(customer)
                .build();

        entityManager.persist(playlist1);
        entityManager.persist(playlist2);

        List<Playlist> expectedList = new ArrayList<>();
        expectedList.add(playlist1);
        expectedList.add(playlist2);

        List<Playlist> actualList = repository.findAll();

        assertEquals(actualList, expectedList);
    }
}
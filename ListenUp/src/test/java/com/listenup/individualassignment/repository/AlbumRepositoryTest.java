package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.entity.*;
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
class AlbumRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AlbumRepository repository;

    Date date = new Date(2021,11,27);

    private Artist saveArtist(String name) {
        Artist artist = Artist.builder()
                .name(name)
                .build();

        entityManager.persist(artist);
        return artist;
    }
    private Genre saveGenre(String name) {
        Genre genre = Genre.builder()
                .name(name)
                .build();

        entityManager.persist(genre);
        return genre;
    }

    @Test
    void save(){
        Artist artist = saveArtist("Maroon 5");

        Album album = Album.builder()
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        Album actualAlbum = repository.save(album);

        actualAlbum = entityManager.find(Album.class, actualAlbum.getId());

        Album expectedAlbum = Album.builder()
                .id(actualAlbum.getId())
                .name("V")
                .artist(artist)
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        assertEquals(actualAlbum, expectedAlbum);
    }
    @Test
    void getAllAlbumSongs(){
        Artist artist = saveArtist("Maroon 5");
        Genre genre = saveGenre("Pop");

        Album album = Album.builder()
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();
        entityManager.persist(album);

        Song song = new AlbumSong("Map", genre, album);
        entityManager.persist(song);

        entityManager.flush();
        entityManager.clear();

        Album savedAlbum = entityManager.find(Album.class, album.getId());

        assertEquals(savedAlbum.getAlbumSongs().size(), List.of(song).size());
    }
    @Test
    void getAllAlbums() {
        Artist artist = saveArtist("Maroon 5");

        Album album1 = Album.builder()
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();
        Album album2 = Album.builder()
                .name("Overexposed")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        entityManager.persist(album1);
        entityManager.persist(album2);

        List<Album> expectList = new ArrayList<>();
        expectList.add(album1);
        expectList.add(album2);

        List<Album> actualList = repository.findAll();

        assertEquals(actualList, expectList);
    }
}
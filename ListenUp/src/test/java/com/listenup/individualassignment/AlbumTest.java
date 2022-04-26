package com.listenup.individualassignment;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import com.listenup.individualassignment.business.imp.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.ArtistDTOConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.business.AlbumService;
import com.listenup.individualassignment.business.ArtistService;
import com.listenup.individualassignment.dto.createupdate.AlbumDTO;
import com.listenup.individualassignment.repository.AlbumRepository;
import com.listenup.individualassignment.repository.ArtistRepository;
import com.listenup.individualassignment.business.imp.AlbumServiceImp;
import com.listenup.individualassignment.business.imp.ArtistServiceImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AlbumTest {
    @Autowired
    AlbumRepository dbAlbum;
    @Autowired
    ArtistRepository dbArtist;

    AlbumService albumMG;
    ArtistService artistMG;


    @BeforeEach
    void  setUp(){
        albumMG = new AlbumServiceImp(dbAlbum);
        artistMG = new ArtistServiceImp(dbArtist);
    }

    //dto: valid input
    @Test
    void albumDTOConverterValidInput() {
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Date date = new Date(2021,11,27);
        Album album = new Album(1, "Overexposed", artist, date, date);
        albumMG.addAlbum(album);

        AlbumDTO albumDTO= new AlbumDTO(1, "Overexposed", ArtistDTOConverter.convertToDTO(artist), date, date);
        assertNotNull(AlbumDTOConverter.convertToModel(albumDTO));
    }
    @Test
    void albumObjConverterValidInput() {
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Date date = new Date(2021,11,27);
        Album album = new Album(1, "Overexposed", artist, date, date);
        albumMG.addAlbum(album);

        assertNotNull(AlbumDTOConverter.convertToDTO(album));
    }
    @Test
    void albumObjConverterForSongValidInput(){
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Date date = new Date(2021,11,27);
        Album album = new Album(1, "Overexposed", artist, date, date);
        albumMG.addAlbum(album);

        assertNotNull(AlbumDTOConverter.convertToDTOForSong(album));
    }
    @Test
    void albumDTOListConverterValidInput() {
        Artist artist = new Artist(1, "Maroon 5");
        Date date = new Date(2021,11,27);
        Album album = new Album(1, "Overexposed", artist, date, date);
        List<Album> albums = new ArrayList<>();
        albums.add(album);

        assertNotNull(AlbumDTOConverter.convertToDTOList(albums));
    }

    //create album: valid input
    @Test
    void addAlbumValidInput() {
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Date date = new Date(2021,11,27);
        Album album = new Album(1, "Overexposed", artist, date, date);
        Album album1 = new Album(2, "V", artist, date, date);
        albumMG.addAlbum(album1);

        assertTrue(albumMG.addAlbum(album));
    }
    //create album: same id
    @Test
    void addAlbumSameID() {
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Date date = new Date(2021,11,27);
        Album album1 = new Album(1, "Overexposed", artist, date, date);
        Album album2 = new Album(1, "V", artist, date, date);
        albumMG.addAlbum(album1);
        assertFalse(albumMG.addAlbum(album2));
    }

    //update album: valid input
    @Test
    void updateAlbumValidInput() {
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Date date = new Date(2021,11,27);
        Album album1 = new Album(1, "Overexposed", artist, date, date);
        Album album2 = new Album(1, "V", artist, date, date);
        albumMG.addAlbum(album1);
        assertTrue(albumMG.editAlbum(album2));
    }
    //update album: album not in database
    @Test
    void updateAlbumInvalidID() {
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Date date = new Date(2021,11,27);
        Album album = new Album(1, "Overexposed", artist, date, date);
        assertFalse(albumMG.editAlbum(album));
    }

    //delete album: valid input
    @Test
    void deleteAlbumValidInput() {
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Date date = new Date(2021,11,27);
        Album album = new Album(1, "Overexposed", artist, date, date);
        albumMG.addAlbum(album);
        assertTrue(albumMG.deleteAlbum(1));
    }

    //delete album: invalid id
    @Test
    void deleteAlbumInvalidID() {
        assertFalse(albumMG.deleteAlbum(100));
    }
}

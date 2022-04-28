package com.listenup.individualassignment;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import com.listenup.individualassignment.business.imp.*;
import com.listenup.individualassignment.business.imp.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.createupdate.AlbumSongDTO;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.listenup.individualassignment.model.*;
import com.listenup.individualassignment.business.SongService;
import com.listenup.individualassignment.business.GenreService;
import com.listenup.individualassignment.business.AlbumService;
import com.listenup.individualassignment.business.ArtistService;
import com.listenup.individualassignment.dto.createupdate.SingleSongDTO;
import com.listenup.individualassignment.repository.SongRepository;
import com.listenup.individualassignment.repository.AlbumRepository;
import com.listenup.individualassignment.repository.GenreRepository;
import com.listenup.individualassignment.repository.ArtistRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SongTest {
    @Autowired
    SongRepository dbSong;
    @Autowired
    GenreRepository dbGenre;
    @Autowired
    ArtistRepository dbArtist;
    @Autowired
    AlbumRepository dbAlbum;

    SongService songMG;
    GenreService genreMG;
    ArtistService artistMG;
    AlbumService albumMG;

    @BeforeEach
    void  setUp(){
        songMG = new SongServiceImp(dbSong);
        genreMG = new GenreServiceImp(dbGenre);
        artistMG = new ArtistServiceImp(dbArtist);
        albumMG = new AlbumServiceImp(dbAlbum);
    }
    //dto: valid input
    @Test
    void singleSongDTOConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        SingleSong song = new SingleSong(1, "Payphone", artist, genre, date, date);
        songMG.addSingleSong(song);

        SingleSongDTO songDTO = new SingleSongDTO(1, "Payphone", ArtistDTOConverter.convertToDTO(artist), GenreDTOConverter.convertToDTO(genre), date, date);
        assertNotNull(SongDTOConverter.convertToSingleSongModel(songDTO));
    }
    void albumSongDTOConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Album album = new Album(1, "V", artist, date, date);
        albumMG.addAlbum(album);
        AlbumSong song = new AlbumSong(1, "Payphone", genre, album);
        songMG.addAlbumSong(song);

        AlbumSongDTO songDTO = new AlbumSongDTO(1, "Payphone", GenreDTOConverter.convertToDTO(genre), AlbumDTOConverter.convertToDTO(album));
        assertNotNull(SongDTOConverter.convertToAlbumSongModel(songDTO));
    }
    @Test
    void songObjConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        Song song = new SingleSong(1, "Payphone", artist, genre, date, date);

        assertNotNull(SongDTOConverter.convertToSingleSongDTO(song));
    }
    @Test
    void songObjConvertorForAlbumSongValidInput() {
        Genre genre = new Genre(1, "Pop");
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        Album album = new Album(1, "V", artist, date, date);
        AlbumSong song = new AlbumSong(1, "Star Boy", genre, album);

        assertNotNull(SongDTOConverter.convertToAlbumSongDTO(song));
    }
    //dto list: valid input
    @Test
    void songDTOListConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        Song song = new SingleSong(1, "Payphone", artist, genre, date, date);
        List<Song> songs = new ArrayList<>();
        songs.add(song);

        assertNotNull(SongDTOConverter.convertToSingleSongDTOList(songs));
    }
    @Test
    void songDTOListForAlbumSongConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        Album album = new Album(1, "V", artist, date, date);
        AlbumSong song = new AlbumSong(1, "Star Boy", genre, album);
        List<AlbumSong> songs = new ArrayList<>();
        songs.add(song);

        assertNotNull(SongDTOConverter.convertToAlbumSongDTOList(songs));
    }
    @Test
    void songObjListConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        SingleSong song = new SingleSong(1, "Payphone", artist, genre, date, date);
        List<SingleSongDTO> songs = new ArrayList<>();
        songs.add(SongDTOConverter.convertToSingleSongDTO(song));

        assertNotNull(SongDTOConverter.convertToSingleSongModelList(songs));
    }
    @Test
    void songObjListForAlbumSongConvertorValidInput() {
        Genre genre = new Genre(1, "Pop");
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        Album album = new Album(1, "V", artist, date, date);
        AlbumSong song = new AlbumSong(1, "Star Boy", genre, album);
        List<AlbumSongDTO> songs = new ArrayList<>();
        songs.add(SongDTOConverter.convertToAlbumSongDTO(song));

        assertNotNull(SongDTOConverter.convertToAlbumSongModelList(songs));
    }

    //create song: valid input
    @Test
    void addSingleSongValidInput() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        SingleSong song = new SingleSong(1, "Payphone", artist, genre, date, date);
        SingleSong song1 = new SingleSong(2 , "One More Night", artist, genre, date, date);
        songMG.addSingleSong(song1);
        assertTrue(songMG.addSingleSong(song));
    }
    //create song: same id
    @Test
    void addSingleSongSameID() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        SingleSong song1 = new SingleSong(1, "Payphone", artist, genre, date, date);
        SingleSong song2 = new SingleSong(1, "Map", artist, genre, date, date);
        songMG.addSingleSong(song1);
        assertFalse(songMG.addSingleSong(song2));
    }
    //create song: valid input
    @Test
    void addAlbumSongValidInput() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Album album = new Album(1, "V", artist, date, date);
        albumMG.addAlbum(album);
        AlbumSong song = new AlbumSong(1, "Payphone", genre, album);
        AlbumSong song1 = new AlbumSong(2 , "One More Night", genre, album);
        songMG.addAlbumSong(song1);
        assertTrue(songMG.addAlbumSong(song));
    }
    //create song: same id
    @Test
    void addAlbumSongSameID() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Album album = new Album(1, "V", artist, date, date);
        albumMG.addAlbum(album);
        SingleSong song1 = new SingleSong(1, "Payphone", artist, genre, date, date);
        AlbumSong song2 = new AlbumSong(1, "Star Boy", genre, album);
        songMG.addSingleSong(song1);
        assertFalse(songMG.addAlbumSong(song2));
    }

    //update song: valid input
    @Test
    void updateSongValidInput() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        SingleSong song = new SingleSong(1, "Payphone", artist, genre, date, date);
        songMG.addSingleSong(song);
        assertTrue(songMG.editSong(song));
    }
    //update song: song not in database
    @Test
    void updateSongInvalidID() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        Song song = new SingleSong(1, "Payphone", artist, genre, date, date);
        assertFalse(songMG.editSong(song));
    }

    //delete song: valid input
    @Test
    void deleteSongValidInput() {
        Genre genre = new Genre(1, "Pop");
        genreMG.addGenre(genre);
        Date date = new Date(2021,11,27);
        Artist artist = new Artist(1, "Maroon 5");
        artistMG.addArtist(artist);
        SingleSong song = new SingleSong(1, "Payphone", artist, genre, date, date);
        songMG.addSingleSong(song);
        assertTrue(songMG.deleteSong(1));
    }

    //delete song: invalid id
    @Test
    void deleteSongInvalidID() {
        assertFalse(songMG.deleteSong(100));
    }
}

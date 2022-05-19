package com.listenup.individualassignment.business.imp.dtoconverter;

import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumSongDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SongDTOConverterTest {
    Artist artist = Artist.builder()
            .id(1l)
            .name("Maroon 5")
            .build();

    Album album = Album.builder()
            .id(1l)
            .name("V")
            .artist(artist)
            .releasedDate(null)
            .uploadedDate(null)
            .build();

    Genre genre = Genre.builder()
            .id(1l)
            .name("Pop")
            .build();

    Song expectedSingleSongModel = new SingleSong(1l, "Sugar", artist, genre, null, null);

    SingleSongDTO expectedSingleSongDTO = SingleSongDTO.builder()
            .id(1l)
            .name("Sugar")
            .genre(GenreDTOConverter.convertToDTO(genre))
            .artist(ArtistDTOConverter.convertToDTO(artist))
            .releasedDate(null)
            .uploadedDate(null)
            .build();

    Song expectedAlbumSongModel = new AlbumSong(1l, "Map", genre, album);

    AlbumSongDTO expectedAlbumSongDTO = AlbumSongDTO.builder()
            .id(1l)
            .name("Map")
            .genre(GenreDTOConverter.convertToDTO(genre))
            .album(AlbumDTOConverter.convertToDTO(album))
            .build();

    @Test
    void convertToSingleSongDTO() {
        SingleSongDTO actualDTO = SongDTOConverter.convertToSingleSongDTO(expectedSingleSongModel);

        assertEquals(actualDTO, expectedSingleSongDTO);
    }

    @Test
    void convertToAlbumSongDTO() {
        AlbumSongDTO actualDTO = SongDTOConverter.convertToAlbumSongDTO((AlbumSong) expectedAlbumSongModel);

        assertEquals(actualDTO, expectedAlbumSongDTO);
    }

    @Test
    void convertToSingleSongModelForUpdate() {
        SingleSong actualModel = SongDTOConverter.convertToSingleSongModelForUpdate(expectedSingleSongDTO);

        assertEquals(actualModel, expectedSingleSongModel);
    }

    @Test
    void convertToSingleSongModelForCreate() {
        CreateSingleSongRequestDTO dto = CreateSingleSongRequestDTO.builder()
                .name("Sugar")
                .genre(GenreDTOConverter.convertToDTO(genre))
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .releasedDate(null)
                .uploadedDate(null)
                .build();

        SingleSong actualModel = SongDTOConverter.convertToSingleSongModelForCreate(dto);
        actualModel.setId(1l);

        assertEquals(actualModel, expectedSingleSongModel);
    }

    @Test
    void convertToAlbumSongModelForUpdate() {
        AlbumSong actualModel = SongDTOConverter.convertToAlbumSongModelForUpdate(expectedAlbumSongDTO);

        assertEquals(actualModel, expectedAlbumSongModel);
    }

    @Test
    void convertToAlbumSongModelForCreate() {
        CreateAlbumSongRequestDTO dto = CreateAlbumSongRequestDTO.builder()
                .name("Map")
                .album(AlbumDTOConverter.convertToDTO(album))
                .genre(GenreDTOConverter.convertToDTO(genre))
                .build();

        AlbumSong actualModel = SongDTOConverter.convertToAlbumSongModelForCreate(dto);

        assertEquals(actualModel, expectedAlbumSongModel);
    }

    @Test
    void convertToSingleSongDTOList() {
        List<Song> singleSongList = new ArrayList<>();
        singleSongList.add(expectedSingleSongModel);

        List<SingleSongDTO> expectedList = new ArrayList<>();
        expectedList.add(expectedSingleSongDTO);

        List<SingleSongDTO> actualList = SongDTOConverter.convertToSingleSongDTOList(singleSongList);

        assertEquals(actualList, expectedList);
    }

    @Test
    void convertToAlbumSongDTOList() {
        List<AlbumSong> albumSongList = new ArrayList<>();
        albumSongList.add((AlbumSong) expectedAlbumSongModel);

        List<AlbumSongDTO> expectedList = new ArrayList<>();
        expectedList.add(expectedAlbumSongDTO);

        List<AlbumSongDTO> actualList = SongDTOConverter.convertToAlbumSongDTOList(albumSongList);

        assertEquals(actualList, expectedList);
    }

    @Test
    void convertToSingleSongModelList() {
        List<SingleSong> expectedList = new ArrayList<>();
        expectedList.add((SingleSong) expectedSingleSongModel);

        List<SingleSongDTO> dtoList = new ArrayList<>();
        dtoList.add(expectedSingleSongDTO);

        List<Song> actualList = SongDTOConverter.convertToSingleSongModelList(dtoList);

        assertEquals(actualList, expectedList);
    }

    @Test
    void convertToAlbumSongModelList() {
        List<AlbumSong> expectedList = new ArrayList<>();
        expectedList.add((AlbumSong) expectedAlbumSongModel);

        List<AlbumSongDTO> dtoList = new ArrayList<>();
        dtoList.add(expectedAlbumSongDTO);

        List<Song> actualList = SongDTOConverter.convertToAlbumSongModelList(dtoList);

        assertEquals(actualList, expectedList);
    }
}
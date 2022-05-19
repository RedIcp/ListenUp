package com.listenup.individualassignment.business.imp.dtoconverter;

import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
import com.listenup.individualassignment.entity.Album;
import com.listenup.individualassignment.entity.Artist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlbumDTOConverterTest {
    Date date = new Date(2021,11,27);

    Artist artist = Artist.builder()
            .id(1l)
            .name("Maroon 5")
            .build();

    Album expectedModel = Album.builder()
            .id(1l)
            .name("V")
            .artist(artist)
            .releasedDate(date)
            .uploadedDate(date)
            .albumSongs(Collections.emptyList())
            .build();

    AlbumDTO expectedDTO = AlbumDTO.builder()
            .id(1l)
            .name("V")
            .artist(ArtistDTOConverter.convertToDTO(artist))
            .releasedDate(date)
            .uploadedDate(date)
            .build();
    @Test
    void convertToDTO() {
        AlbumDTO actualDTO = AlbumDTOConverter.convertToDTO(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToModelForUpdate() {
        Album actualModel = AlbumDTOConverter.convertToModelForUpdate(expectedDTO);
        actualModel.setAlbumSongs(Collections.emptyList());

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToModelForCreate() {
        CreateAlbumRequestDTO dto = CreateAlbumRequestDTO.builder()
                .name("V")
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        Album actualModel = AlbumDTOConverter.convertToModelForCreate(dto);
        actualModel.setAlbumSongs(Collections.emptyList());
        actualModel.setId(1l);

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToDTOForSong() {
        AlbumSongListDTO expectedDTOForSongs = AlbumSongListDTO.builder()
                .id(1l)
                .name("V")
                .songs(Collections.emptyList())
                .build();

        AlbumSongListDTO actualDTOForSongs = AlbumDTOConverter.convertToDTOForSong(expectedModel);

        assertEquals(actualDTOForSongs, expectedDTOForSongs);
    }

    @Test
    void convertToDTOList() {
        Album album1 = Album.builder()
                .id(1l)
                .name("V")
                .artist(artist)
                .releasedDate(date)
                .uploadedDate(date)
                .albumSongs(Collections.emptyList())
                .build();
        Album album2 = Album.builder()
                .id(2l)
                .name("Overexposed")
                .artist(artist)
                .releasedDate(date)
                .uploadedDate(date)
                .albumSongs(Collections.emptyList())
                .build();

        List<Album> albums = new ArrayList<>();
        albums.add(album1);
        albums.add(album2);

        List<AlbumDTO> expectedDTOList = new ArrayList<>();
        expectedDTOList.add(AlbumDTOConverter.convertToDTO(album1));
        expectedDTOList.add(AlbumDTOConverter.convertToDTO(album2));

        List<AlbumDTO> actualDTOList = AlbumDTOConverter.convertToDTOList(albums);

        assertEquals(actualDTOList, expectedDTOList);
    }
}
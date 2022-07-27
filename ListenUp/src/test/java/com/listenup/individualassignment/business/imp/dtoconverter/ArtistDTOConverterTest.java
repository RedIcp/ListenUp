package com.listenup.individualassignment.business.imp.dtoconverter;

import com.listenup.individualassignment.business.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.ArtistAlbumListDTO;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistRequestDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import com.listenup.individualassignment.entity.Artist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistDTOConverterTest {
    final Artist expectedModel = Artist.builder()
            .id(1L)
            .name("Maroon 5")
            .albums(Collections.emptyList())
            .songs(Collections.emptyList())
            .build();

    final ArtistDTO expectedDTO = ArtistDTO.builder()
            .id(1L)
            .name("Maroon 5")
            .build();

    @Test
    void convertToDTO() {
        ArtistDTO actualDTO = ArtistDTOConverter.convertToDTO(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToModelForUpdate() {
        Artist actualModel = ArtistDTOConverter.convertToModelForUpdate(expectedDTO);
        actualModel.setAlbums(Collections.emptyList());
        actualModel.setSongs(Collections.emptyList());

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToModelForCreate() {
        CreateArtistRequestDTO dto = CreateArtistRequestDTO.builder()
                .name("Maroon 5")
                .build();

        Artist actualModel = ArtistDTOConverter.convertToModelForCreate(dto);
        actualModel.setId(1L);
        actualModel.setAlbums(Collections.emptyList());
        actualModel.setSongs(Collections.emptyList());

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToDTOForSong() {
        ArtistSongListDTO expectedDTO = ArtistSongListDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .songs(Collections.emptyList())
                .build();

        ArtistSongListDTO actualDTO = ArtistDTOConverter.convertToDTOForSong(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToDTOForAlbum() {
        ArtistAlbumListDTO expectedDTO = ArtistAlbumListDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .albums(Collections.emptyList())
                .build();

        ArtistAlbumListDTO actualDTO = ArtistDTOConverter.convertToDTOForAlbum(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToDTOList() {
        Artist artist1 = Artist.builder()
                .id(1L)
                .name("Maroon 5")
                .albums(Collections.emptyList())
                .songs(Collections.emptyList())
                .build();
        Artist artist2 = Artist.builder()
                .id(2L)
                .name("Post Malone")
                .albums(Collections.emptyList())
                .songs(Collections.emptyList())
                .build();

        List<ArtistDTO> expectedList = new ArrayList<>();
        expectedList.add(ArtistDTOConverter.convertToDTO(artist1));
        expectedList.add(ArtistDTOConverter.convertToDTO(artist2));

        List<Artist> artistList = new ArrayList<>();
        artistList.add(artist1);
        artistList.add(artist2);

        List<ArtistDTO> actualList = ArtistDTOConverter.convertToDTOList(artistList);

        assertEquals(actualList, expectedList);
    }
}
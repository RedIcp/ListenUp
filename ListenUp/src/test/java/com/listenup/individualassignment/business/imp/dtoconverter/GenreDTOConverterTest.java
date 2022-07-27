package com.listenup.individualassignment.business.imp.dtoconverter;

import com.listenup.individualassignment.business.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreRequestDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.entity.Genre;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreDTOConverterTest {
    final Genre expectedModel = Genre.builder()
            .id(1L)
            .name("Pop")
            .songs(Collections.emptyList())
            .build();

    final GenreDTO expectedDTO = GenreDTO.builder()
            .id(1L)
            .name("Pop")
            .build();

    @Test
    void convertToDTO() {
        GenreDTO actualDTO = GenreDTOConverter.convertToDTO(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToModelForUpdate() {
        Genre actualModel = GenreDTOConverter.convertToModelForUpdate(expectedDTO);
        actualModel.setSongs(Collections.emptyList());

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToModelForCreate() {
        CreateGenreRequestDTO dto = CreateGenreRequestDTO.builder()
                .name("Pop")
                .build();

        Genre actualModel = GenreDTOConverter.convertToModelForCreate(dto);
        actualModel.setId(1L);
        actualModel.setSongs(Collections.emptyList());

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToDTOForSong() {
        GenreSongListDTO actualDTO = GenreSongListDTO.builder()
                .id(1L)
                .name("Pop")
                .songs(Collections.emptyList())
                .build();

        GenreSongListDTO expectedDTO = GenreDTOConverter.convertToDTOForSong(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToDTOList() {
        Genre genre1 = Genre.builder()
                .id(1L)
                .name("Pop")
                .songs(Collections.emptyList())
                .build();
        Genre genre2 = Genre.builder()
                .id(1L)
                .name("Hip-hop")
                .songs(Collections.emptyList())
                .build();

        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre1);
        genreList.add(genre2);

        List<GenreDTO> expectedList = new ArrayList<>();
        expectedList.add(GenreDTOConverter.convertToDTO(genre1));
        expectedList.add(GenreDTOConverter.convertToDTO(genre2));

        List<GenreDTO> actualList = GenreDTOConverter.convertToDTOList(genreList);

        assertEquals(actualList, expectedList);
    }
}
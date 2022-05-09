package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.exception.InvalidGenreException;
import com.listenup.individualassignment.business.imp.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.model.Genre;
import com.listenup.individualassignment.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceImpTest {
    @Mock
    private GenreRepository repository;
    @InjectMocks
    private GenreServiceImp service;

    @Test
    void addGenre() {
        Genre genre = Genre.builder()
                .name("Pop")
                .build();
        Genre savedGenre = Genre.builder()
                .id(1l)
                .name("Pop")
                .build();

        when(repository.save(genre)).thenReturn(savedGenre);

        CreateGenreDTO expectedDTO = CreateGenreDTO.builder()
                .name("Pop")
                .build();

        CreateGenreDTO actualDTO = service.addGenre(expectedDTO);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).save(genre);
    }

    @Test
    void getGenres() {
        Genre genre1 = Genre.builder()
                .id(1l)
                .name("Pop")
                .build();
        Genre genre2 = Genre.builder()
                .id(2l)
                .name("Hip-hop")
                .build();

        when(repository.findAll()).thenReturn(List.of(genre1, genre2));

        List<GenreDTO> expectedList = new ArrayList<>();
        expectedList.add(GenreDTOConverter.convertToDTO(genre1));
        expectedList.add(GenreDTOConverter.convertToDTO(genre2));

        List<GenreDTO> actualList = service.getGenres();

        assertEquals(actualList, expectedList);
    }

    @Test
    void getGenreSongs() {
        Genre genre = Genre.builder()
                .id(1l)
                .name("Pop")
                .songs(Collections.emptyList())
                .build();

        when(repository.getById(1l)).thenReturn(genre);

        GenreSongListDTO expectedDTO = GenreSongListDTO.builder()
                .id(1l)
                .name("Pop")
                .songs(Collections.emptyList())
                .build();

        GenreSongListDTO actualDTO = service.getGenreSongs(1l);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void editGenreValid() {
        when(repository.existsById(1l)).thenReturn(true);

        GenreDTO updateDTO = GenreDTO.builder()
                .id(1l)
                .name("Hip-hop")
                .build();

        service.editGenre(updateDTO);

        verify(repository).existsById(1l);

        Genre actualGenre = Genre.builder()
                .id(1l)
                .name("Hip-hop")
                .build();

        verify(repository).save(actualGenre);
    }

    @Test
    void editGenreInvalid() {
        when(repository.existsById(1l)).thenReturn(false);

        GenreDTO updateDTO = GenreDTO.builder()
                .id(1l)
                .name("Hip-hop")
                .build();

        InvalidGenreException exception = assertThrows(InvalidGenreException.class, () -> service.editGenre(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).existsById(1l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteGenreValid() {
        when(repository.existsById(1l)).thenReturn(true);

        service.deleteGenre(1l);

        verify(repository).existsById(1l);
        verify(repository).deleteById(1l);
    }

    @Test
    void deleteGenreInvalid() {
        when(repository.existsById(1l)).thenReturn(false);

        service.deleteGenre(1l);

        verify(repository).existsById(1l);
        verifyNoMoreInteractions(repository);
    }
}
package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.exception.InvalidGenreException;
import com.listenup.individualassignment.business.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.business.genre.*;
import com.listenup.individualassignment.business.genre.imp.*;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.entity.Genre;
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
    private CreateGenreUseCaseImp createGenreUseCase;
    @InjectMocks
    private GetGenresUseCaseImp getGenresUseCase;
    @InjectMocks
    private GetGenreSongsUseCaseImp getGenreSongsUseCase;
    @InjectMocks
    private DeleteGenreUseCaseImp deleteGenreUseCase;
    @InjectMocks
    private UpdateGenreUseCaseImp updateGenreUseCase;

    @Test
    void addGenre() {
        Genre genre = Genre.builder()
                .name("Pop")
                .build();
        Genre savedGenre = Genre.builder()
                .id(1L)
                .name("Pop")
                .build();

        when(repository.save(genre)).thenReturn(savedGenre);

        CreateGenreRequestDTO dto = CreateGenreRequestDTO.builder()
                .name("Pop")
                .build();

        CreateGenreResponseDTO expectedDTO = CreateGenreResponseDTO.builder()
                .genreID(1L)
                .build();

        CreateGenreResponseDTO actualDTO = createGenreUseCase.addGenre(dto);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).save(genre);
    }

    @Test
    void getGenres() {
        Genre genre1 = Genre.builder()
                .id(1L)
                .name("Pop")
                .build();
        Genre genre2 = Genre.builder()
                .id(2L)
                .name("Hip-hop")
                .build();

        when(repository.findAll()).thenReturn(List.of(genre1, genre2));

        List<GenreDTO> expectedList = new ArrayList<>();
        expectedList.add(GenreDTOConverter.convertToDTO(genre1));
        expectedList.add(GenreDTOConverter.convertToDTO(genre2));

        List<GenreDTO> actualList = getGenresUseCase.getGenres();

        assertEquals(actualList, expectedList);

        verify(repository).findAll();
    }

    @Test
    void getGenreSongs() {
        Genre genre = Genre.builder()
                .id(1L)
                .name("Pop")
                .songs(Collections.emptyList())
                .build();

        when(repository.getById(1L)).thenReturn(genre);

        GenreSongListDTO expectedDTO = GenreSongListDTO.builder()
                .id(1L)
                .name("Pop")
                .songs(Collections.emptyList())
                .build();

        GenreSongListDTO actualDTO = getGenreSongsUseCase.getGenreSongs(1L);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).getById(1L);
    }

    @Test
    void editGenreValid() {
        when(repository.existsById(1L)).thenReturn(true);

        GenreDTO updateDTO = GenreDTO.builder()
                .id(1L)
                .name("Hip-hop")
                .build();

        updateGenreUseCase.editGenre(updateDTO);

        verify(repository).existsById(1L);

        Genre actualGenre = Genre.builder()
                .id(1L)
                .name("Hip-hop")
                .build();

        verify(repository).save(actualGenre);
    }

    @Test
    void editGenreInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        GenreDTO updateDTO = GenreDTO.builder()
                .id(1L)
                .name("Hip-hop")
                .build();

        InvalidGenreException exception = assertThrows(InvalidGenreException.class, () -> updateGenreUseCase.editGenre(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteGenreValid() {
        when(repository.existsById(1L)).thenReturn(true);

        deleteGenreUseCase.deleteGenre(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteGenreInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        deleteGenreUseCase.deleteGenre(1L);

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }
}
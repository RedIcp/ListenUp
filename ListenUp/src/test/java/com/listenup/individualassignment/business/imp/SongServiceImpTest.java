package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.exception.InvalidSongException;
import com.listenup.individualassignment.business.imp.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.GenreDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongResponseDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.entity.*;
import com.listenup.individualassignment.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongServiceImpTest {
    @Mock
    private SongRepository repository;

    @InjectMocks
    private SongServiceImp service;

    Artist artist = Artist.builder()
            .id(1L)
            .name("Maroon 5")
            .build();

    Album album = Album.builder()
            .id(1L)
            .name("V")
            .artist(artist)
            .releasedDate(null)
            .uploadedDate(null)
            .build();

    Genre genre = Genre.builder()
            .id(1L)
            .name("Pop")
            .build();

    Date date = new Date(2021,11,27);

    @Test
    void addSingleSong() {
        Song song = new SingleSong("Sugar", artist, genre, date, date);

        Song savedSong = new SingleSong(1L,"Sugar", artist, genre, date, date);

        when(repository.save(song)).thenReturn(savedSong);

        CreateSingleSongRequestDTO dto = CreateSingleSongRequestDTO.builder()
                .name("Sugar")
                .genre(GenreDTOConverter.convertToDTO(genre))
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        CreateSingleSongResponseDTO expectedDTO = CreateSingleSongResponseDTO.builder()
                .singleSongID(1L)
                .build();

        CreateSingleSongResponseDTO actualDTO = service.addSingleSong(dto);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).save(song);
    }

    @Test
    void addAlbumSong() {
        Song song = new AlbumSong("Map", genre, album);

        Song savedSong = new AlbumSong(1L,"Map", genre, album);

        when(repository.save(song)).thenReturn(savedSong);

        CreateAlbumSongRequestDTO dto = CreateAlbumSongRequestDTO.builder()
                .name("Map")
                .genre(GenreDTOConverter.convertToDTO(genre))
                .album(AlbumDTOConverter.convertToDTO(album))
                .build();

        CreateAlbumSongResponseDTO expectedDTO = CreateAlbumSongResponseDTO.builder()
                .albumSongID(1L)
                .build();

        CreateAlbumSongResponseDTO actualDTO = service.addAlbumSong(dto);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).save(song);
    }

    @Test
    void getSongs() {
        Song song1 = new SingleSong(1L,"Sugar", artist, genre, date, date);
        Song song2 = new AlbumSong(1L,"Map", genre, album);

        when(repository.findAll()).thenReturn(List.of(song1, song2));

        List<SingleSongDTO> expectedList = new ArrayList<>();
        expectedList.add(SongDTOConverter.convertToSingleSongDTO(song1));
        expectedList.add(SongDTOConverter.convertToSingleSongDTO(song2));

        List<SingleSongDTO> actualList = service.getSongs();

        assertEquals(actualList, expectedList);
    }

    @Test
    void getSong() {
        Song song = new SingleSong(1L,"Sugar", artist, genre, date, date);

        when(repository.getById(1L)).thenReturn(song);

        SingleSongDTO expectedDTO = SingleSongDTO.builder()
                .id(1L)
                .name("Sugar")
                .genre(GenreDTOConverter.convertToDTO(genre))
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        SingleSongDTO actualDTO = service.getSong(1L);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void editSongValid() {
        when(repository.existsById(1L)).thenReturn(true);

        SingleSongDTO updateDTO = SingleSongDTO.builder()
                .id(1L)
                .name("Lost Stars")
                .genre(GenreDTOConverter.convertToDTO(genre))
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        service.editSong(updateDTO);

        Song actualSong = new SingleSong(1L,"Lost Stars", artist, genre, date, date);

        verify(repository).save(actualSong);
    }

    @Test
    void editSongInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        SingleSongDTO updateDTO = SingleSongDTO.builder()
                .id(1L)
                .name("Lost Stars")
                .genre(GenreDTOConverter.convertToDTO(genre))
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        InvalidSongException exception = assertThrows(InvalidSongException.class, () -> service.editSong(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteSongValid() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteSong(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteSongInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        service.deleteSong(1L);

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }
}
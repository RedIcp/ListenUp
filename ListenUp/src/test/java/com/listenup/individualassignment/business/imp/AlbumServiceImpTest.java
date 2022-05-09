package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.exception.InvalidAlbumException;
import com.listenup.individualassignment.business.imp.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceImpTest {
    @Mock
    private AlbumRepository repository;
    @InjectMocks
    private AlbumServiceImp service;

    Date date = new Date(2021,11,27);

    Artist artist = Artist.builder()
            .id(1l)
            .name("Maroon 5")
            .build();
    @Test
    void addAlbum() {
        Album album = Album.builder()
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();
        Album savedAlbum = Album.builder()
                .id(1l)
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        when(repository.save(album)).thenReturn(savedAlbum);

        CreateAlbumDTO expectedDTO = CreateAlbumDTO.builder()
                .name("V")
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .releasedDate(date)
                .uploadedDate(date)
                .build();
        CreateAlbumDTO actualDTO = service.addAlbum(expectedDTO);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).save(album);
    }

    @Test
    void getAlbums() {
        Album album1 = Album.builder()
                .id(1l)
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();
        Album album2 = Album.builder()
                .id(2l)
                .name("Overexposed")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        when(repository.findAll()).thenReturn(List.of(album1, album2));

        List<AlbumDTO> expectedList = new ArrayList<>();
        expectedList.add(AlbumDTOConverter.convertToDTO(album1));
        expectedList.add(AlbumDTOConverter.convertToDTO(album2));

        List<AlbumDTO> actualList = service.getAlbums();

        assertEquals(actualList, expectedList);
    }

    @Test
    void getAlbumSongsWithValidInput() {
        Album album = Album.builder()
                .id(1l)
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .albumSongs(Collections.emptyList())
                .build();

        when(repository.getById(1l)).thenReturn(album);

        AlbumSongListDTO expectedDTO = AlbumSongListDTO.builder()
                .id(1l)
                .name("V")
                .songs(Collections.emptyList())
                .build();

        AlbumSongListDTO actualDTO = service.getAlbumSongs(1l);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void editAlbumValidInput() {
        when(repository.existsById(1l)).thenReturn(true);

        AlbumDTO updateDTO = AlbumDTO.builder()
                .id(1l)
                .name("Map")
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        service.editAlbum(updateDTO);

        verify(repository).existsById(1l);

        Album actualAlbum = Album.builder()
                .id(1l)
                .name("Map")
                .artist(artist)
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        verify(repository).save(actualAlbum);
    }
    @Test
    void editAlbumInvalidInput() {
        when(repository.existsById(1l)).thenReturn(false);

        AlbumDTO updateDTO = AlbumDTO.builder()
                .id(1l)
                .name("Map")
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        InvalidAlbumException exception = assertThrows(InvalidAlbumException.class, () -> service.editAlbum(updateDTO));

        assertEquals("INVALID_ALBUM", exception.getReason());

        verify(repository).existsById(1l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteAlbumValid() {
        when(repository.existsById(10L)).thenReturn(true);

        service.deleteAlbum(10L);

        verify(repository).existsById(10l);
        verify(repository).deleteById(10L);
    }

    @Test
    void deleteAlbumInvalid() {
        when(repository.existsById(10L)).thenReturn(false);

        service.deleteAlbum(10L);

        verify(repository).existsById(10l);
        verifyNoMoreInteractions(repository);
    }
}
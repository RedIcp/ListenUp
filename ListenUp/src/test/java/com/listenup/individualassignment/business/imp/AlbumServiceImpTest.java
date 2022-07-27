package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.album.*;
import com.listenup.individualassignment.business.album.imp.*;
import com.listenup.individualassignment.business.exception.InvalidAlbumException;
import com.listenup.individualassignment.business.dtoconverter.AlbumDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
import com.listenup.individualassignment.entity.Album;
import com.listenup.individualassignment.entity.Artist;
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
    private CreateAlbumUseCaseImp createAlbumUseCase;
    @InjectMocks
    private GetAlbumsUseCaseImp getAlbumsUseCase;
    @InjectMocks
    private GetAlbumSongsUseCaseImp getAlbumSongsUseCase;
    @InjectMocks
    private DeleteAlbumUseCaseImp deleteAlbumUseCase;
    @InjectMocks
    private UpdateAlbumUseCaseImp updateAlbumUseCase;

    private final Date date = new Date(2021,11,27);

    private final Artist artist = Artist.builder()
            .id(1L)
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
                .id(1L)
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        when(repository.save(album)).thenReturn(savedAlbum);

        CreateAlbumRequestDTO dto = CreateAlbumRequestDTO.builder()
                .name("V")
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        CreateAlbumResponseDTO expectedDTO = CreateAlbumResponseDTO.builder()
                .albumID(1L)
                .build();

        CreateAlbumResponseDTO actualDTO = createAlbumUseCase.addAlbum(dto);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).save(album);
    }

    @Test
    void getAlbums() {
        Album album1 = Album.builder()
                .id(1L)
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();
        Album album2 = Album.builder()
                .id(2L)
                .name("Overexposed")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        when(repository.findAll()).thenReturn(List.of(album1, album2));

        List<AlbumDTO> expectedList = new ArrayList<>();
        expectedList.add(AlbumDTOConverter.convertToDTO(album1));
        expectedList.add(AlbumDTOConverter.convertToDTO(album2));

        List<AlbumDTO> actualList = getAlbumsUseCase.getAlbums();

        assertEquals(actualList, expectedList);

        verify(repository).findAll();
    }

    @Test
    void getAlbumSongsWithValidInput() {
        Album album = Album.builder()
                .id(1L)
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .albumSongs(Collections.emptyList())
                .build();

        when(repository.getById(1L)).thenReturn(album);

        AlbumSongListDTO expectedDTO = AlbumSongListDTO.builder()
                .id(1L)
                .name("V")
                .songs(Collections.emptyList())
                .build();

        AlbumSongListDTO actualDTO = getAlbumSongsUseCase.getAlbumSongs(1L);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).getById(1L);
    }

    @Test
    void editAlbumValidInput() {
        when(repository.existsById(1L)).thenReturn(true);

        AlbumDTO updateDTO = AlbumDTO.builder()
                .id(1L)
                .name("Map")
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        updateAlbumUseCase.editAlbum(updateDTO);

        verify(repository).existsById(1L);

        Album actualAlbum = Album.builder()
                .id(1L)
                .name("Map")
                .artist(artist)
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        verify(repository).save(actualAlbum);
    }
    @Test
    void editAlbumInvalidInput() {
        when(repository.existsById(1L)).thenReturn(false);

        AlbumDTO updateDTO = AlbumDTO.builder()
                .id(1L)
                .name("Map")
                .artist(ArtistDTOConverter.convertToDTO(artist))
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        InvalidAlbumException exception = assertThrows(InvalidAlbumException.class, () -> updateAlbumUseCase.editAlbum(updateDTO));

        assertEquals("INVALID_ALBUM", exception.getReason());

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteAlbumValid() {
        when(repository.existsById(10L)).thenReturn(true);

        deleteAlbumUseCase.deleteAlbum(10L);

        verify(repository).existsById(10L);
        verify(repository).deleteById(10L);
    }

    @Test
    void deleteAlbumInvalid() {
        when(repository.existsById(10L)).thenReturn(false);

        deleteAlbumUseCase.deleteAlbum(10L);

        verify(repository).existsById(10L);
        verifyNoMoreInteractions(repository);
    }
}
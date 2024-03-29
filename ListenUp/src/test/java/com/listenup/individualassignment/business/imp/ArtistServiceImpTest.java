package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.artist.imp.*;
import com.listenup.individualassignment.business.exception.InvalidArtistException;
import com.listenup.individualassignment.business.dtoconverter.ArtistDTOConverter;
import com.listenup.individualassignment.dto.ArtistAlbumListDTO;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import com.listenup.individualassignment.repository.entity.Artist;
import com.listenup.individualassignment.repository.ArtistRepository;
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
class ArtistServiceImpTest {
    @Mock
    private ArtistRepository repository;

    @InjectMocks
    private CreateArtistUseCaseImp createArtistUseCase;
    @InjectMocks
    private GetArtistsUseCaseImp getArtistsUseCase;
    @InjectMocks
    private GetArtistSongsUseCaseImp getArtistSongsUseCase;
    @InjectMocks
    private GetArtistAlbumsUseCaseImp getArtistAlbumsUseCase;
    @InjectMocks
    private DeleteArtistUseCaseImp deleteArtistUseCase;
    @InjectMocks
    private UpdateArtistUseCaseImp updateArtistUseCase;

    @Test
    void addArtist() {
        Artist artist = Artist.builder()
                .name("Maroon 5")
                .build();
        Artist savedArtist = Artist.builder()
                .id(1L)
                .name("Maroon 5")
                .build();

        when(repository.save(artist)).thenReturn(savedArtist);

        CreateArtistRequestDTO dto = CreateArtistRequestDTO.builder()
                .name("Maroon 5")
                .build();

        CreateArtistResponseDTO expectedDTO = CreateArtistResponseDTO.builder()
                .artistID(1L)
                .build();

        CreateArtistResponseDTO actualDTO = createArtistUseCase.addArtist(dto);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).save(artist);
    }

    @Test
    void getArtists() {
        Artist artist1 = Artist.builder()
                .id(1L)
                .name("Maroon 5")
                .build();
        Artist artist2 = Artist.builder()
                .id(2L)
                .name("Post Malone")
                .build();

        when(repository.findAll()).thenReturn(List.of(artist1,artist2));

        List<ArtistDTO> expectedList = new ArrayList<>();
        expectedList.add(ArtistDTOConverter.convertToDTO(artist1));
        expectedList.add(ArtistDTOConverter.convertToDTO(artist2));

        List<ArtistDTO> actualList = getArtistsUseCase.getArtists();

        assertEquals(actualList, expectedList);

        verify(repository).findAll();
    }

    @Test
    void getArtistSongs() {
        Artist artist = Artist.builder()
                .id(1L)
                .name("Maroon 5")
                .songs(Collections.emptyList())
                .build();

        when(repository.getById(1L)).thenReturn(artist);

        ArtistSongListDTO expectedDTO = ArtistSongListDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .songs(Collections.emptyList())
                .build();

        ArtistSongListDTO actualDTO = getArtistSongsUseCase.getArtistSongs(1L);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).getById(1L);
    }

    @Test
    void getArtistAlbums() {
        Artist artist = Artist.builder()
                .id(1L)
                .name("Maroon 5")
                .albums(Collections.emptyList())
                .build();

        when(repository.getById(1L)).thenReturn(artist);

        ArtistAlbumListDTO expectedDTO = ArtistAlbumListDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .albums(Collections.emptyList())
                .build();

        ArtistAlbumListDTO actualDTO = getArtistAlbumsUseCase.getArtistAlbums(1L);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).getById(1L);
    }

    @Test
    void editArtistValid() {
        when(repository.existsById(1L)).thenReturn(true);

        ArtistDTO updateDTO = ArtistDTO.builder()
                .id(1L)
                .name("Post Malone")
                .build();

        updateArtistUseCase.editArtist(updateDTO);

        verify(repository).existsById(1L);

        Artist actualArtist = Artist.builder()
                .id(1L)
                .name("Post Malone")
                .build();

        verify(repository).save(actualArtist);
    }

    @Test
    void editArtistInvalidInput(){
        when(repository.existsById(1L)).thenReturn(false);

        ArtistDTO updateDTO = ArtistDTO.builder()
                .id(1L)
                .name("Post Malone")
                .build();

        InvalidArtistException exception = assertThrows(InvalidArtistException.class, () -> updateArtistUseCase.editArtist(updateDTO));

        assertEquals("INVALID_ARTIST", exception.getReason());

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteArtistValid() {
        when(repository.existsById(1L)).thenReturn(true);

        deleteArtistUseCase.deleteArtist(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deleteArtistInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        deleteArtistUseCase.deleteArtist(1L);

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }
}
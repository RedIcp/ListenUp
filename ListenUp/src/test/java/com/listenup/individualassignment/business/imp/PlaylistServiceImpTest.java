package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.business.exception.UnauthorizedDataAccessException;
import com.listenup.individualassignment.business.imp.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.AccessTokenDTO;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.entity.*;
import com.listenup.individualassignment.repository.PlaylistRepository;
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
class PlaylistServiceImpTest {
    @Mock
    private PlaylistRepository repository;
    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private PlaylistServiceImp service;

    final Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

    @Test
    void addPlaylist() {
        Playlist playlist = Playlist.builder()
                .name("Chill")
                .customer(customer)
                .build();
        Playlist savedPlaylist = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .build();

        when(repository.save(playlist)).thenReturn(savedPlaylist);

        CreatePlaylistRequestDTO dto = CreatePlaylistRequestDTO.builder()
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        CreatePlaylistResponseDTO expectedDTO = CreatePlaylistResponseDTO.builder()
                .playlistID(1L)
                .build();

        CreatePlaylistResponseDTO actualDTO = service.addPlaylist(dto);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).save(playlist);
    }

    @Test
    void getPlaylists() {
        Playlist playlist1 = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .build();
        Playlist playlist2 = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .build();

        when(repository.findAll()).thenReturn(List.of(playlist1, playlist2));

        List<PlaylistDTO> expectedList = new ArrayList<>();
        expectedList.add(PlaylistDTOConverter.convertToDTO(playlist1));
        expectedList.add(PlaylistDTOConverter.convertToDTO(playlist2));

        List<PlaylistDTO> actualList = service.getPlaylists();

        assertEquals(actualList, expectedList);
    }

    @Test
    void getPlaylistSong() {
        Playlist playlist = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .songs(Collections.emptyList())
                .build();

        when(repository.getById(1L)).thenReturn(playlist);

        PlaylistSongListDTO expectedDTO = PlaylistSongListDTO.builder()
                .id(1L)
                .customer("Yellow")
                .name("Chill")
                .songs(Collections.emptyList())
                .build();

        PlaylistSongListDTO actualDTO = service.getPlaylistSong(1L);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void editPlaylistValid() {
        when(repository.existsById(1L)).thenReturn(true);
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);

        PlaylistDTO updateDTO = PlaylistDTO.builder()
                .id(1L)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        service.editPlaylist(updateDTO);

        verify(repository).existsById(1L);

        Playlist actualPlaylist = Playlist.builder()
                .id(1L)
                .name("Workout")
                .customer(customer)
                .build();

        verify(repository).save(actualPlaylist);
    }

    @Test
    void editPlaylistInvalid() {
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.existsById(1L)).thenReturn(false);

        PlaylistDTO updateDTO = PlaylistDTO.builder()
                .id(1L)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        InvalidPlaylistException exception = assertThrows(InvalidPlaylistException.class, () -> service.editPlaylist(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void editPlaylistUnauthorised() {
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(false);
        when(requestAccessToken.getUserID()).thenReturn(4L);

        PlaylistDTO updateDTO = PlaylistDTO.builder()
                .id(1L)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        UnauthorizedDataAccessException exception = assertThrows(UnauthorizedDataAccessException.class, () -> service.editPlaylist(updateDTO));

        assertEquals("USER_ID_NOT_FROM_LOGGED_IN_USER", exception.getReason());

        verifyNoMoreInteractions(repository);
    }

    @Test
    void addSongToPlaylistValid() {
        Playlist beforeUpdatePlaylist = new Playlist(1L, "Chill", customer);

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(beforeUpdatePlaylist);
        when(repository.existsById(1L)).thenReturn(true);

        ArtistDTO artist = ArtistDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .build();

        GenreDTO genre = GenreDTO.builder()
                .id(1L)
                .name("Pop")
                .build();

        SingleSongDTO song = SingleSongDTO.builder()
                .id(1L)
                .name("Payphone")
                .artist(artist)
                .genre(genre)
                .uploadedDate(null)
                .releasedDate(null)
                .build();

        AddRemoveSongToPlaylistDTO updateDTO = AddRemoveSongToPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .song(song)
                .build();

        service.addSongToPlaylist(updateDTO);

        Playlist actualPlaylist = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .songs(List.of(SongDTOConverter.convertToSingleSongModelForUpdate(song)))
                .build();

        verify(repository).save(actualPlaylist);
    }

    @Test
    void addSongToPlaylistInvalid() {
        when(repository.existsById(1L)).thenReturn(false);
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);

        AddRemoveSongToPlaylistDTO updateDTO = AddRemoveSongToPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .song(null)
                .build();

        InvalidPlaylistException exception = assertThrows(InvalidPlaylistException.class, () -> service.addSongToPlaylist(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void removeSongFromPlaylistValid() {
        Playlist beforeUpdatePlaylist = new Playlist(1L, "Chill", customer);

        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);
        when(repository.getById(1L)).thenReturn(beforeUpdatePlaylist);
        when(repository.existsById(1L)).thenReturn(true);

        ArtistDTO artist = ArtistDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .build();

        GenreDTO genre = GenreDTO.builder()
                .id(1L)
                .name("Pop")
                .build();

        SingleSongDTO song = SingleSongDTO.builder()
                .id(1L)
                .name("Payphone")
                .artist(artist)
                .genre(genre)
                .uploadedDate(null)
                .releasedDate(null)
                .build();

        AddRemoveSongToPlaylistDTO updateDTO = AddRemoveSongToPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .song(song)
                .build();

        service.removeSongFromPlaylist(updateDTO);

        Playlist actualPlaylist = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .songs(Collections.emptyList())
                .build();

        verify(repository).save(actualPlaylist);
    }

    @Test
    void removeSongFromPlaylistInvalid() {
        when(repository.existsById(1L)).thenReturn(false);
        when(requestAccessToken.hasRole(RoleEnum.ADMIN.name())).thenReturn(true);

        AddRemoveSongToPlaylistDTO updateDTO = AddRemoveSongToPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .song(null)
                .build();

        InvalidPlaylistException exception = assertThrows(InvalidPlaylistException.class, () -> service.removeSongFromPlaylist(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deletePlaylistValid() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deletePlaylist(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deletePlaylistInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        service.deletePlaylist(1L);

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }
}
package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.business.imp.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
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

    @InjectMocks
    private PlaylistServiceImp service;

    Customer customer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

    @Test
    void addPlaylist() {
        Playlist playlist = Playlist.builder()
                .name("Chill")
                .customer(customer)
                .build();
        Playlist savedPlaylist = Playlist.builder()
                .id(1l)
                .name("Chill")
                .customer(customer)
                .build();

        when(repository.save(playlist)).thenReturn(savedPlaylist);

        CreatePlaylistRequestDTO dto = CreatePlaylistRequestDTO.builder()
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        CreatePlaylistResponseDTO expectedDTO = CreatePlaylistResponseDTO.builder()
                .playlistID(1l)
                .build();

        CreatePlaylistResponseDTO actualDTO = service.addPlaylist(dto);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).save(playlist);
    }

    @Test
    void getPlaylists() {
        Playlist playlist1 = Playlist.builder()
                .id(1l)
                .name("Chill")
                .customer(customer)
                .build();
        Playlist playlist2 = Playlist.builder()
                .id(1l)
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
                .id(1l)
                .name("Chill")
                .customer(customer)
                .songs(Collections.emptyList())
                .build();

        when(repository.getById(1l)).thenReturn(playlist);

        PlaylistSongListDTO expectedDTO = PlaylistSongListDTO.builder()
                .id(1l)
                .name("Chill")
                .songs(Collections.emptyList())
                .build();

        PlaylistSongListDTO actualDTO = service.getPlaylistSong(1l);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void editPlaylistValid() {
        when(repository.existsById(1l)).thenReturn(true);

        PlaylistDTO updateDTO = PlaylistDTO.builder()
                .id(1l)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        service.editPlaylist(updateDTO);

        verify(repository).existsById(1l);

        Playlist actualPlaylist = Playlist.builder()
                .id(1l)
                .name("Workout")
                .customer(customer)
                .build();

        verify(repository).save(actualPlaylist);
    }

    @Test
    void editPlaylistInvalid() {
        when(repository.existsById(1l)).thenReturn(false);

        PlaylistDTO updateDTO = PlaylistDTO.builder()
                .id(1l)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        InvalidPlaylistException exception = assertThrows(InvalidPlaylistException.class, () -> service.editPlaylist(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).existsById(1l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void editPlaylistSongsValid() {
        Playlist beforeUpdatePlaylist = Playlist.builder()
                .id(1l)
                .name("Chill")
                .customer(customer)
                .songs(Collections.emptyList())
                .build();

        when(repository.getById(1l)).thenReturn(beforeUpdatePlaylist);
        when(repository.existsById(1l)).thenReturn(true);

        ArtistDTO artist = ArtistDTO.builder()
                .id(1l)
                .name("Maroon 5")
                .build();

        GenreDTO genre = GenreDTO.builder()
                .id(1l)
                .name("Pop")
                .build();

        SingleSongDTO song = SingleSongDTO.builder()
                .id(1l)
                .name("Payphone")
                .artist(artist)
                .genre(genre)
                .uploadedDate(null)
                .releasedDate(null)
                .build();

        PlaylistSongListDTO updateDTO = PlaylistSongListDTO.builder()
                .id(1l)
                .name("Chill")
                .songs(List.of(song))
                .build();

        service.editPlaylistSongs(updateDTO);

        Playlist actualPlaylist = Playlist.builder()
                .id(1l)
                .name("Chill")
                .customer(customer)
                .songs(List.of(SongDTOConverter.convertToSingleSongModelForUpdate(song)))
                .build();

        verify(repository).save(actualPlaylist);
    }

    @Test
    void editPlaylistSongsInvalid() {
        when(repository.existsById(1l)).thenReturn(false);

        PlaylistSongListDTO updateDTO = PlaylistSongListDTO.builder()
                .id(1l)
                .name("Chill")
                .songs(Collections.emptyList())
                .build();

        InvalidPlaylistException exception = assertThrows(InvalidPlaylistException.class, () -> service.editPlaylistSongs(updateDTO));

        assertEquals("INVALID_ID", exception.getReason());

        verify(repository).getById(1l);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deletePlaylistValid() {
        when(repository.existsById(1l)).thenReturn(true);

        service.deletePlaylist(1l);

        verify(repository).existsById(1l);
        verify(repository).deleteById(1l);
    }

    @Test
    void deletePlaylistInvalid() {
        when(repository.existsById(1l)).thenReturn(false);

        service.deletePlaylist(1l);

        verify(repository).existsById(1l);
        verifyNoMoreInteractions(repository);
    }
}
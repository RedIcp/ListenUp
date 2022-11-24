package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.business.exception.InvalidSongException;
import com.listenup.individualassignment.business.exception.UnauthorizedDataAccessException;
import com.listenup.individualassignment.business.playlist.GetPlaylist;
import com.listenup.individualassignment.business.song.GetSong;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.dto.AccessTokenDTO;
import com.listenup.individualassignment.repository.PlaylistRepository;
import com.listenup.individualassignment.repository.SongRepository;
import com.listenup.individualassignment.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UniversalUseCaseImpTest {
    @Mock
    private SongRepository songRepository;
    @Mock
    private PlaylistRepository playlistRepository;
    @Mock
    private AccessTokenDTO requestAccessToken;

    @InjectMocks
    private GetSong getSong;
    @InjectMocks
    private GetPlaylist getPlaylist;
    @InjectMocks
    private IsAuthorised isAuthorised;

    final Artist artist = Artist.builder()
            .id(1L)
            .name("Maroon 5")
            .build();

    final Genre genre = Genre.builder()
            .id(1L)
            .name("Pop")
            .build();

    final Date date = new Date(2021,11,27);

    final Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

    @Test
    void getSongValid() {
        when(songRepository.existsById(1L)).thenReturn(true);

        Song expected = new SingleSong(1L,"Sugar", artist, genre, date, date);

        when(songRepository.getById(1L)).thenReturn(expected);

        Song actual = getSong.getSong(1L);

        assertEquals(expected, actual);

        verify(songRepository).existsById(1L);
        verify(songRepository).getById(1L);
    }

    @Test
    void getSongInvalid() {
        when(songRepository.existsById(1L)).thenReturn(false);

        InvalidSongException exception = assertThrows(InvalidSongException.class, () -> getSong.getSong(1L));

        assertEquals("INVALID_SONG_ID", exception.getReason());

        verify(songRepository).existsById(1L);
    }

    @Test
    void getPlaylistValid() {
        when(playlistRepository.existsById(1L)).thenReturn(true);

        Playlist expected = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .build();

        when(playlistRepository.getById(1L)).thenReturn(expected);

        Playlist actual = getPlaylist.getPlaylist(1L);

        assertEquals(expected, actual);

        verify(playlistRepository).existsById(1L);
        verify(playlistRepository).getById(1L);
    }

    @Test
    void getPlaylistInvalid() {
        when(playlistRepository.existsById(1L)).thenReturn(false);

        InvalidPlaylistException exception = assertThrows(InvalidPlaylistException.class, () -> getPlaylist.getPlaylist(1L));

        assertEquals("INVALID_PLAYLIST", exception.getReason());
    }

    @Test
    void isAuthorised() {
        when(requestAccessToken.hasRole("ADMIN")).thenReturn(true);

        assertDoesNotThrow(() ->isAuthorised.isAuthorised(1L));
    }

    @Test
    void isNotAuthorised() {
        when(requestAccessToken.hasRole("ADMIN")).thenReturn(false);
        when(requestAccessToken.getUserID()).thenReturn(2L);

        UnauthorizedDataAccessException exception = assertThrows(UnauthorizedDataAccessException.class, () -> isAuthorised.isAuthorised(1L));

        assertEquals("USER_ID_NOT_FROM_LOGGED_IN_USER", exception.getReason());
    }
}

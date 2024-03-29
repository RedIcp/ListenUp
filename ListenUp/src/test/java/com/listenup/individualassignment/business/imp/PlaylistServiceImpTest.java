package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.exception.InvalidPlaylistException;
import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.business.exception.SongAlreadyExistsInPlaylistException;
import com.listenup.individualassignment.business.playlist.imp.*;
import com.listenup.individualassignment.business.song.GetSong;
import com.listenup.individualassignment.business.user.IsAuthorised;
import com.listenup.individualassignment.business.user.action.imp.AddLikedPlaylistUseCaseImp;
import com.listenup.individualassignment.business.user.action.imp.LikeUnlikePlaylistUseCaseImp;
import com.listenup.individualassignment.business.user.action.imp.RemoveLikedPlaylistUseCaseImp;
import com.listenup.individualassignment.dto.AccessTokenDTO;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
import com.listenup.individualassignment.repository.PlaylistRepository;
import com.listenup.individualassignment.repository.entity.Customer;
import com.listenup.individualassignment.repository.entity.Playlist;
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
    @Mock
    private IsAuthorised authorised;
    @Mock
    private GetSong getSong;
    @Mock
    private AddLikedPlaylistUseCaseImp add;
    @Mock
    private RemoveLikedPlaylistUseCaseImp remove;

    @InjectMocks
    private CreatePlaylistUseCaseImp createPlaylistUseCase;
    @InjectMocks
    private GetPlaylistsUseCaseImp getPlaylistsUseCase;
    @InjectMocks
    private GetPlaylistSongsUseCaseImp getPlaylistSongsUseCase;
    @InjectMocks
    private DeletePlaylistUseCaseImp deletePlaylistUseCase;
    @InjectMocks
    private UpdatePlaylistUseCaseImp updatePlaylistUseCase;
    @InjectMocks
    private AddSongToPlaylistUseCaseImp addSongToPlaylistUseCase;
    @InjectMocks
    private RemoveSongFromPlaylistUseCaseImp removeSongFromPlaylistUseCase;
    @InjectMocks
    private LikeUnlikePlaylistUseCaseImp likeUnlikePlaylistUseCase;

    final Customer customer = new Customer(1L, "Yellow", "yellow@gmail.com", "123Yellow");

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

        CreatePlaylistResponseDTO actualDTO = createPlaylistUseCase.addPlaylist(dto);

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

        List<PlaylistDTO> actualList = getPlaylistsUseCase.getPlaylists();

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

        PlaylistSongListDTO actualDTO = getPlaylistSongsUseCase.getPlaylistSongs(1L);

        assertEquals(actualDTO, expectedDTO);

        verify(repository).getById(1L);
    }

    @Test
    void editPlaylistValid() {
        when(repository.existsById(1L)).thenReturn(true);

        PlaylistDTO updateDTO = PlaylistDTO.builder()
                .id(1L)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        updatePlaylistUseCase.editPlaylist(updateDTO);

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
        when(repository.existsById(1L)).thenReturn(false);

        PlaylistDTO updateDTO = PlaylistDTO.builder()
                .id(1L)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        InvalidPlaylistException exception = assertThrows(InvalidPlaylistException.class, () -> updatePlaylistUseCase.editPlaylist(updateDTO));

        assertEquals("INVALID_PLAYLIST", exception.getReason());

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addSongToPlaylistValid() {
        Playlist beforeUpdatePlaylist = new Playlist(1L, "Chill", customer);

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
                .songID(1L)
                .build();

        addSongToPlaylistUseCase.addSongToPlaylist(updateDTO);

        Playlist actualPlaylist = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .songs(List.of(SongDTOConverter.convertToSingleSongModelForUpdate(song)))
                .build();

        verify(repository).getById(1L);
        verify(repository).existsById(1L);
    }

    @Test
    void addSongToPlaylistInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveSongToPlaylistDTO updateDTO = AddRemoveSongToPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .songID(1L)
                .build();

        InvalidPlaylistException exception = assertThrows(InvalidPlaylistException.class, () -> addSongToPlaylistUseCase.addSongToPlaylist(updateDTO));

        assertEquals("INVALID_PLAYLIST", exception.getReason());

        verify(repository).getById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addSongToPlaylistSongAlreadyExists() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.songExistInPlaylist(1L, 1L)).thenReturn(1);

        AddRemoveSongToPlaylistDTO updateDTO = AddRemoveSongToPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .songID(1L)
                .build();

        SongAlreadyExistsInPlaylistException exception = assertThrows(SongAlreadyExistsInPlaylistException.class, () -> addSongToPlaylistUseCase.addSongToPlaylist(updateDTO));

        assertEquals("SONG_ALREADY_IN_PLAYLIST", exception.getReason());

        verify(repository).getById(1L);
        verify(repository).songExistInPlaylist(1L, 1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void removeSongFromPlaylistValid() {
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
                .songID(1L)
                .build();

        removeSongFromPlaylistUseCase.removeSongFromPlaylist(updateDTO);

        Playlist actualPlaylist = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .songs(Collections.emptyList())
                .build();

        verify(repository).removePlaylistSong(1L, 1L);
    }

    @Test
    void removeSongFromPlaylistInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        AddRemoveSongToPlaylistDTO updateDTO = AddRemoveSongToPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .songID(1L)
                .build();

        InvalidPlaylistException exception = assertThrows(InvalidPlaylistException.class, () -> removeSongFromPlaylistUseCase.removeSongFromPlaylist(updateDTO));

        assertEquals("INVALID_PLAYLIST", exception.getReason());

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deletePlaylistValid() {
        when(repository.existsById(1L)).thenReturn(true);

        deletePlaylistUseCase.deletePlaylist(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void deletePlaylistInvalid() {
        when(repository.existsById(1L)).thenReturn(false);

        deletePlaylistUseCase.deletePlaylist(1L);

        verify(repository).existsById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void likePlaylist() {
        when(repository.playlistLiked(1L, 1L)).thenReturn(0);

        AddRemoveLikedPlaylistDTO playlist = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .build();

        likeUnlikePlaylistUseCase.likeUnlikePlaylist(playlist);

        verify(add).addLikedPlaylist(playlist);
    }

    @Test
    void unLikePlaylist() {
        when(repository.playlistLiked(1L, 1L)).thenReturn(1);

        AddRemoveLikedPlaylistDTO playlist = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .build();

        likeUnlikePlaylistUseCase.likeUnlikePlaylist(playlist);

        verify(remove).removeLikedPlaylist(playlist);
    }

    @Test
    void likeUnLikePlaylistRandomNumber() {
        when(repository.playlistLiked(1L, 1L)).thenReturn(5);

        AddRemoveLikedPlaylistDTO playlist = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .build();

        assertDoesNotThrow(()->likeUnlikePlaylistUseCase.likeUnlikePlaylist(playlist));

        verifyNoMoreInteractions(repository);
    }
}
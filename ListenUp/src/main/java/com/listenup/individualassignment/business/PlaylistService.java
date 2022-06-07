package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;

public interface PlaylistService {
    CreatePlaylistResponseDTO addPlaylist(CreatePlaylistRequestDTO playlist);
    List<PlaylistDTO> getPlaylists();
    PlaylistSongListDTO getPlaylistSong(long id);
    PlaylistDTO editPlaylist(PlaylistDTO playlist);
    void editPlaylistSongs(AddRemoveSongToPlaylistDTO song);
    boolean deletePlaylist(long id);
}

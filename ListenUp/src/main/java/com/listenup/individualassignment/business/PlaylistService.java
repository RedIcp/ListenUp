package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;

public interface PlaylistService {
    CreatePlaylistDTO addPlaylist(CreatePlaylistDTO playlist);
    List<PlaylistDTO> getPlaylists();
    PlaylistSongListDTO getPlaylistSong(long id);
    PlaylistDTO editPlaylist(PlaylistDTO playlist);
    PlaylistSongListDTO editPlaylistSongs(PlaylistSongListDTO playlist);
    boolean deletePlaylist(long id);
}

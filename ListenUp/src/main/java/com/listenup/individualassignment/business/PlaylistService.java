package com.listenup.individualassignment.business;

import com.listenup.individualassignment.dto.PlaylistDTO;
import com.listenup.individualassignment.model.Playlist;

import java.util.List;

public interface PlaylistService {
    Playlist playlistDTOConvertor(PlaylistDTO dto);
    boolean addPlaylist(Playlist playlist);
    List<Playlist> getPlaylists();
    boolean editPlaylist(Playlist playlist);
    boolean deletePlaylist(long id);
    Playlist getPlaylist(long id);
}

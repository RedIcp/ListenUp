package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.Playlist;

public interface PlaylistService {
    boolean addPlaylist(Playlist playlist);
    List<Playlist> getPlaylists();
    Playlist getPlaylist(long id);
    boolean editPlaylist(Playlist playlist);
    boolean deletePlaylist(long id);
}

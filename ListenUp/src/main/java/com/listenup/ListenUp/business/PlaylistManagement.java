package com.listenup.ListenUp.business;

import com.listenup.ListenUp.model.Playlist;

import java.util.List;

public interface PlaylistManagement {
    boolean addPlaylist(Playlist playlist);
    List<Playlist> getPlaylists();
    boolean editPlaylist(Playlist playlist);
    boolean deletePlaylist(int id);
    Playlist getPlaylist(int id);
}

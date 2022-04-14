package com.listenup.individualassignment.business;

import com.listenup.individualassignment.model.Playlist;

import java.util.List;

public interface PlaylistService {
    boolean addPlaylist(Playlist playlist);
    List<Playlist> getPlaylists();
    boolean editPlaylist(Playlist playlist);
    boolean deletePlaylist(int id);
    Playlist getPlaylist(int id);
}

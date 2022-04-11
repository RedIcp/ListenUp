package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.Playlist;

import java.util.List;

public interface PlaylistRepository {
    boolean addPlaylist(int id, String name, boolean isPublic);
    List<Playlist> getPlaylists();
    boolean editPlaylist(int id, String name, boolean isPublic);
    boolean deletePlaylist(int id);
}

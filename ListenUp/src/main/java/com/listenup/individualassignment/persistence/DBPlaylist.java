package com.listenup.individualassignment.persistence;

import com.listenup.individualassignment.model.Playlist;

import java.util.List;

public interface DBPlaylist {
    boolean addPlaylist(int id, String name, boolean isPublic);
    List<Playlist> getPlaylists();
    boolean editPlaylist(int id, String name, boolean isPublic);
    boolean deletePlaylist(int id);
}

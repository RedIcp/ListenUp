package com.listenup.ListenUp.business;

import com.listenup.ListenUp.model.Album;

import java.util.List;

public interface AlbumManagement {
    boolean addAlbum(Album album);
    List<Album> getAlbums();
    boolean editAlbum(Album album);
    boolean deleteAlbum(int id);
    Album getAlbum(int id);
}

package com.listenup.ListenUp.business;

import com.listenup.ListenUp.model.Album;

import java.util.List;

public interface AlbumManagment {
    boolean addAlbum(Album album);
    List<Album> getAlbum();
    boolean editAlbum(Album album);
    boolean deleteAlbum(int id);
    Album getAlbum(int id);
}

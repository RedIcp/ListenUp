package com.listenup.individualassignment.business;

import com.listenup.individualassignment.model.Album;

import java.util.List;

public interface AlbumService {
    boolean addAlbum(Album album);
    List<Album> getAlbums();
    boolean editAlbum(Album album);
    boolean deleteAlbum(int id);
    Album getAlbum(int id);
}

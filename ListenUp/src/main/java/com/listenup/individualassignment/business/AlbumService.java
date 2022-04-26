package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.Album;

public interface AlbumService {
    boolean addAlbum(Album album);
    List<Album> getAlbums();
    Album getAlbum(long id);
    boolean editAlbum(Album album);
    boolean deleteAlbum(long id);
}

package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.model.Artist;

import java.util.Date;
import java.util.List;

public interface AlbumRepository {
    boolean addAlbum(int id, String name, Artist artist, Date releasedDate, Date uploadedDate);
    List<Album> getAlbums();
    boolean editAlbum(int id, String name, Artist artist, Date releasedDate, Date uploadedDate);
    boolean deleteAlbum(int id);
}

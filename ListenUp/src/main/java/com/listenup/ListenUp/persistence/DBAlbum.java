package com.listenup.ListenUp.persistence;

import com.listenup.ListenUp.model.Album;
import com.listenup.ListenUp.model.Artist;

import java.util.Date;
import java.util.List;

public interface DBAlbum {
    boolean addAlbum(int id, String name, Artist artist, Date releasedDate, Date uploadedDate);
    List<Album> getAlbums();
    boolean editAlbum(int id, String name, Artist artist, Date releasedDate, Date uploadedDate);
    boolean deleteAlbum(int id);
}

package com.listenup.ListenUp.persistence;

import com.listenup.ListenUp.model.Artist;

import java.util.List;

public interface DBArtist {
    boolean addArtist(int id, String name);
    List<Artist> getArtists();
    boolean editArtist(int id, String name);
    boolean deleteArtist(int id);
}

package com.listenup.individualassignment.persistence;

import com.listenup.individualassignment.model.Artist;

import java.util.List;

public interface DBArtist {
    boolean addArtist(int id, String name);
    List<Artist> getArtists();
    boolean editArtist(int id, String name);
    boolean deleteArtist(int id);
}

package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.Artist;

import java.util.List;

public interface ArtistRepository {
    boolean addArtist(int id, String name);
    List<Artist> getArtists();
    boolean editArtist(int id, String name);
    boolean deleteArtist(int id);
}

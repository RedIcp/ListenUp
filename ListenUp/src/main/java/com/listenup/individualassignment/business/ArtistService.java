package com.listenup.individualassignment.business;

import com.listenup.individualassignment.model.Artist;

import java.util.List;

public interface ArtistService {
    boolean addArtist(Artist artist);
    List<Artist> getArtists();
    boolean editArtist(Artist artist);
    boolean deleteArtist(int id);
    Artist getArtist(int id);
}

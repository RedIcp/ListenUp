package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.Artist;

public interface ArtistService {
    boolean addArtist(Artist artist);
    List<Artist> getArtists();
    Artist getArtist(long id);
    boolean editArtist(Artist artist);
    boolean deleteArtist(long id);
}

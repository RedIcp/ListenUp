package com.listenup.ListenUp.business;

import com.listenup.ListenUp.model.Artist;

import java.util.List;

public interface ArtistManagment {
    boolean addArtist(Artist artist);
    List<Artist> getArtists();
    boolean editArtist(Artist artist);
    boolean deleteArtist(int id);
    Artist getArtist(int id);
}

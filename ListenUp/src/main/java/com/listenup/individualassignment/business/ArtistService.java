package com.listenup.individualassignment.business;

import com.listenup.individualassignment.dto.ArtistDTO;
import com.listenup.individualassignment.model.Artist;

import java.util.List;

public interface ArtistService {
    Artist artistDTOConvertor(ArtistDTO dto);
    boolean addArtist(Artist artist);
    List<Artist> getArtists();
    boolean editArtist(Artist artist);
    boolean deleteArtist(long id);
    Artist getArtist(long id);
}

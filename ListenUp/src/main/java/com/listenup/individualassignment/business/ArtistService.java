package com.listenup.individualassignment.business;

import java.util.List;
import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.dto.CreateUpdate.ArtistDTO;

public interface ArtistService {
    //dto
    Artist artistDTOConvertor(ArtistDTO dto);

    //crud
    boolean addArtist(Artist artist);
    List<Artist> getArtists();
    Artist getArtist(long id);
    boolean editArtist(Artist artist);
    boolean deleteArtist(long id);
}

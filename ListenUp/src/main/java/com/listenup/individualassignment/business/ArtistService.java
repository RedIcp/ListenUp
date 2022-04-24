package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.dto.createupdate.ArtistDTO;

public interface ArtistService {
    //dto
    Artist artistDTOConvertor(ArtistDTO dto);
    ArtistSongListDTO artistObjConvertor(Artist artist);
    List<ArtistDTO> getArtistDTOS(List<Artist> artists);

    //crud
    boolean addArtist(Artist artist);
    List<Artist> getArtists();
    Artist getArtist(long id);
    boolean editArtist(Artist artist);
    boolean deleteArtist(long id);
}

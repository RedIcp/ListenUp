package com.listenup.individualassignment.business.artist;

import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;

import java.util.List;

public interface GetArtistsUseCase {
    List<ArtistDTO> getArtists();
}

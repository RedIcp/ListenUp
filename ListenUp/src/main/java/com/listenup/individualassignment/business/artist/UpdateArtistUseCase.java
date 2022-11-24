package com.listenup.individualassignment.business.artist;

import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;

public interface UpdateArtistUseCase {
    ArtistDTO editArtist(ArtistDTO artist);
}

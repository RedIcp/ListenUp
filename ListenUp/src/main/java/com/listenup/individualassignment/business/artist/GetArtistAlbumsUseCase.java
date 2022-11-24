package com.listenup.individualassignment.business.artist;

import com.listenup.individualassignment.dto.ArtistAlbumListDTO;

public interface GetArtistAlbumsUseCase {
    ArtistAlbumListDTO getArtistAlbums(long id);
}

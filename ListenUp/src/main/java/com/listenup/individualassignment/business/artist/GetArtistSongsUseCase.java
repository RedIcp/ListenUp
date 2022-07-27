package com.listenup.individualassignment.business.artist;

import com.listenup.individualassignment.dto.ArtistSongListDTO;

public interface GetArtistSongsUseCase {
    ArtistSongListDTO getArtistSongs(long id);
}

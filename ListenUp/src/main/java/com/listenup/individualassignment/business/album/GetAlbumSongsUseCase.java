package com.listenup.individualassignment.business.album;

import com.listenup.individualassignment.dto.AlbumSongListDTO;

public interface GetAlbumSongsUseCase {
    AlbumSongListDTO getAlbumSongs(long id);
}

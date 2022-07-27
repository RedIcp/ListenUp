package com.listenup.individualassignment.business.playlist;

import com.listenup.individualassignment.dto.PlaylistSongListDTO;

public interface GetPlaylistSongsUseCase {
    PlaylistSongListDTO getPlaylistSongs(long id);
}

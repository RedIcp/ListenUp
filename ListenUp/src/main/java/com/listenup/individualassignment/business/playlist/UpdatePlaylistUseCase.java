package com.listenup.individualassignment.business.playlist;

import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;

public interface UpdatePlaylistUseCase {
    PlaylistDTO editPlaylist(PlaylistDTO playlist);
}

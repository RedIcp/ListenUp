package com.listenup.individualassignment.business.playlist;

import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;

import java.util.List;

public interface GetPlaylistsUseCase {
    List<PlaylistDTO> getPlaylists();
}

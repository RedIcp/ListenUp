package com.listenup.individualassignment.business.playlist;

import com.listenup.individualassignment.dto.createdto.AddRemoveSongToPlaylistDTO;

public interface AddSongToPlaylistUseCase {
    void addSongToPlaylist(AddRemoveSongToPlaylistDTO song);
}

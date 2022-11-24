package com.listenup.individualassignment.business.user.action;

import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;

public interface RemoveLikedPlaylistUseCase {
    void removeLikedPlaylist(AddRemoveLikedPlaylistDTO playlist);
}

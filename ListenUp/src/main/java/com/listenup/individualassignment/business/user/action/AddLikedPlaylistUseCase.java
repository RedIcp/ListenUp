package com.listenup.individualassignment.business.user.action;

import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;

public interface AddLikedPlaylistUseCase {
    void addLikedPlaylist(AddRemoveLikedPlaylistDTO playlist);
}

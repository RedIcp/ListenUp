package com.listenup.individualassignment.business.user.action;

import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;

public interface GetUserLikedPlaylistsUseCase {
    CustomerLikedPlaylistListDTO getCustomerLikedPlaylists(long id);
}

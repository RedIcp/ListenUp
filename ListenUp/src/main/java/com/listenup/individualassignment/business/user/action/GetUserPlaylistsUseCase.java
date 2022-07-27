package com.listenup.individualassignment.business.user.action;

import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;

public interface GetUserPlaylistsUseCase {
    CustomerPlaylistListDTO getCustomerPlaylists(long id);
}

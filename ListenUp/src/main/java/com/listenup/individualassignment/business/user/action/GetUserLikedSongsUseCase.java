package com.listenup.individualassignment.business.user.action;

import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;

public interface GetUserLikedSongsUseCase {
    CustomerLikedSongListDTO getCustomerCollection(long id);
}

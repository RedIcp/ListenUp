package com.listenup.individualassignment.business.user.action;

import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;

public interface LikeUnlikeSongUseCase {
    void likeUnlikeSong(AddRemoveSongToCollectionDTO song);
}

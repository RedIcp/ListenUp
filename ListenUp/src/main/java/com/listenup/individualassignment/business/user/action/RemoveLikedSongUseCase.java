package com.listenup.individualassignment.business.user.action;

import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;

public interface RemoveLikedSongUseCase {
    void removeSongFromCollection(AddRemoveSongToCollectionDTO song);
}

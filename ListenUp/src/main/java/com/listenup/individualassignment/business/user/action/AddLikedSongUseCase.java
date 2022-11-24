package com.listenup.individualassignment.business.user.action;

import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;

public interface AddLikedSongUseCase {
    void addSongToCollection(AddRemoveSongToCollectionDTO song);
}

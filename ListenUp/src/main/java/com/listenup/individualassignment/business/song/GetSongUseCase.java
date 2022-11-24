package com.listenup.individualassignment.business.song;

import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;

public interface GetSongUseCase {
    SingleSongDTO getSong(long id);
}

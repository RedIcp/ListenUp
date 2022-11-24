package com.listenup.individualassignment.business.song;

import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;

public interface UpdateSongUseCase {
    SingleSongDTO editSong(SingleSongDTO song);
}

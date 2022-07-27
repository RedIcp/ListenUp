package com.listenup.individualassignment.business.song;

import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;

import java.util.List;

public interface GetSongsUseCase {
    List<SingleSongDTO> getSongs();
}

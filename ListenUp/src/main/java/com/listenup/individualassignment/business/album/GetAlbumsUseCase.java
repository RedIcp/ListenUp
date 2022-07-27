package com.listenup.individualassignment.business.album;

import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;

import java.util.List;

public interface GetAlbumsUseCase {
    List<AlbumDTO> getAlbums();
}

package com.listenup.individualassignment.business.album;

import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;

public interface UpdateAlbumUseCase {
    AlbumDTO editAlbum(AlbumDTO album);
}

package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;

public interface AlbumService {
    CreateAlbumResponseDTO addAlbum(CreateAlbumRequestDTO album);
    List<AlbumDTO> getAlbums();
    AlbumSongListDTO getAlbumSongs(long id);
    AlbumDTO editAlbum(AlbumDTO album);
    boolean deleteAlbum(long id);
}

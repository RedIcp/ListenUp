package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;

public interface AlbumService {
    CreateAlbumDTO addAlbum(CreateAlbumDTO album);
    List<AlbumDTO> getAlbums();
    AlbumSongListDTO getAlbumSongs(long id);
    AlbumDTO editAlbum(AlbumDTO album);
    boolean deleteAlbum(long id);
}

package com.listenup.individualassignment.business;

import com.listenup.individualassignment.dto.AlbumDTO;
import com.listenup.individualassignment.model.Album;

import java.util.List;

public interface AlbumService {
    Album albumDTOConvertor(AlbumDTO dto);
    boolean addAlbum(Album album);
    List<Album> getAlbums();
    boolean editAlbum(Album album);
    boolean deleteAlbum(long id);
    Album getAlbum(long id);
}

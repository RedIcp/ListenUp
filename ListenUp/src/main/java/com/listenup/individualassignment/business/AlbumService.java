package com.listenup.individualassignment.business;

import java.util.List;
import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.dto.CreateUpdate.AlbumDTO;

public interface AlbumService {
    //dto
    Album albumDTOConvertor(AlbumDTO dto);

    //crud
    boolean addAlbum(Album album);
    List<Album> getAlbums();
    Album getAlbum(long id);
    boolean editAlbum(Album album);
    boolean deleteAlbum(long id);
}

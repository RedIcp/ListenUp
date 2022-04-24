package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.createupdate.AlbumDTO;

public interface AlbumService {
    //dto
    Album albumDTOConvertor(AlbumDTO dto);
    AlbumSongListDTO albumObjConvertor(Album album);
    List<AlbumDTO> getAlbumDTOs(List<Album> albums);

    //crud
    boolean addAlbum(Album album);
    List<Album> getAlbums();
    Album getAlbum(long id);
    boolean editAlbum(Album album);
    boolean deleteAlbum(long id);
}

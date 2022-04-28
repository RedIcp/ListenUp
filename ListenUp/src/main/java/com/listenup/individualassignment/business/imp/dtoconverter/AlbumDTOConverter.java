package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.model.Album;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.createupdate.AlbumDTO;

public class AlbumDTOConverter {
    private AlbumDTOConverter(){

    }
    public static AlbumDTO convertToDTO(Album album){
        return AlbumDTO.builder()
                .id(album.getId())
                .name(album.getName())
                .artist(ArtistDTOConverter.convertToDTO(album.getArtist()))
                .releasedDate(album.getReleasedDate())
                .uploadedDate(album.getUploadedDate())
                .build();
    }
    public static Album convertToModel(AlbumDTO album){
        return Album.builder()
                .id(album.getId())
                .name(album.getName())
                .artist(ArtistDTOConverter.convertToModel(album.getArtist()))
                .releasedDate(album.getReleasedDate())
                .uploadedDate(album.getUploadedDate())
                .build();
    }
    public static AlbumSongListDTO convertToDTOForSong(Album album){
        return AlbumSongListDTO.builder()
                .id(album.getId())
                .songs(SongDTOConverter.convertToAlbumSongDTOList(album.getAlbumSongs()))
                .build();
    }
    public static List<AlbumDTO> convertToDTOList(List<Album> albums){
        List<AlbumDTO> dtoList = new ArrayList<>();
        for (Album album : albums){
            dtoList.add(convertToDTO(album));
        }
        return dtoList;
    }
}

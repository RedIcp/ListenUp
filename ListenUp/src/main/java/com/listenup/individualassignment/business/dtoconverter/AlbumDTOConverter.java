package com.listenup.individualassignment.business.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.repository.entity.Album;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;

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
    public static Album convertToModelForUpdate(AlbumDTO album){
        return Album.builder()
                .id(album.getId())
                .name(album.getName())
                .artist(ArtistDTOConverter.convertToModelForUpdate(album.getArtist()))
                .releasedDate(album.getReleasedDate())
                .uploadedDate(album.getUploadedDate())
                .build();
    }
    public static Album convertToModelForCreate(CreateAlbumRequestDTO album){
        return Album.builder()
                .name(album.getName())
                .artist(ArtistDTOConverter.convertToModelForUpdate(album.getArtist()))
                .releasedDate(album.getReleasedDate())
                .uploadedDate(album.getUploadedDate())
                .build();
    }
    public static AlbumSongListDTO convertToDTOForSong(Album album){
        return AlbumSongListDTO.builder()
                .id(album.getId())
                .name(album.getName())
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

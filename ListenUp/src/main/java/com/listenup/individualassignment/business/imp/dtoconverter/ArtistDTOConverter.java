package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.model.Artist;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.dto.ArtistAlbumListDTO;
import com.listenup.individualassignment.dto.createupdate.ArtistDTO;

public class ArtistDTOConverter {
    private ArtistDTOConverter(){

    }
    public static ArtistDTO convertToDTO(Artist artist){
        return ArtistDTO.builder()
                .id(artist.getId())
                .name(artist.getName())
                .build();
    }
    public static Artist convertToModel(ArtistDTO artist){
        return Artist.builder()
                .id(artist.getId())
                .name(artist.getName())
                .build();
    }
    public static ArtistSongListDTO convertToDTOForSong(Artist artist){
        return ArtistSongListDTO.builder()
                .id(artist.getId())
                .songs(SongDTOConverter.convertToDTOList(artist.getSongs()))
                .build();
    }
    public static ArtistAlbumListDTO convertToDTOForAlbum(Artist artist){
        return ArtistAlbumListDTO.builder()
                .id(artist.getId())
                .albums(AlbumDTOConverter.convertToDTOList(artist.getAlbums()))
                .build();
    }
    public static List<ArtistDTO> convertToDTOList(List<Artist> artists){
        List<ArtistDTO> dtoList = new ArrayList<>();
        for (Artist artist : artists){
            dtoList.add(convertToDTO(artist));
        }
        return dtoList;
    }
}

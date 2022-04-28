package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.model.Playlist;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createupdate.PlaylistDTO;

public class PlaylistDTOConverter {
    private PlaylistDTOConverter(){

    }
    public static PlaylistDTO convertToDTO(Playlist playlist){
        return PlaylistDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .customer(CustomerDTOConverter.convertToDTO(playlist.getCustomer()))
                .build();
    }
    public static Playlist convertToModel(PlaylistDTO playlist){
        return Playlist.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .customer(CustomerDTOConverter.convertToModel(playlist.getCustomer()))
                .build();
    }
    public static PlaylistSongListDTO convertToDTOForSong(Playlist playlist){
        return PlaylistSongListDTO.builder()
                .id(playlist.getId())
                .songs(SongDTOConverter.convertToSingleSongDTOList(playlist.getSongs()))
                .build();
    }
    public static List<PlaylistDTO> convertToDTOList(List<Playlist> playlists){
        List<PlaylistDTO> dtoList = new ArrayList<>();
        for(Playlist playlist : playlists){
            dtoList.add(convertToDTO(playlist));
        }
        return dtoList;
    }
}

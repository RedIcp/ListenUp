package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.entity.Playlist;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;

public class PlaylistDTOConverter {
    private PlaylistDTOConverter(){

    }
    public static PlaylistDTO convertToDTO(Playlist playlist){
        return PlaylistDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .customer(CustomerDTOConverter.convertToDTOForUpdate(playlist.getCustomer()))
                .build();
    }
    public static Playlist convertToModelForUpdate(PlaylistDTO playlist){
        return Playlist.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .customer(CustomerDTOConverter.convertToModelForUpdate(playlist.getCustomer()))
                .build();
    }
    public static Playlist convertToModelForCreate(CreatePlaylistRequestDTO playlist){
        return Playlist.builder()
                .name(playlist.getName())
                .customer(CustomerDTOConverter.convertToModelForUpdate(playlist.getCustomer()))
                .build();
    }
    public static PlaylistSongListDTO convertToDTOForSong(Playlist playlist){
        return PlaylistSongListDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
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
    public static List<Playlist> convertToModelList(List<PlaylistDTO> playlists){
        List<Playlist> playlistList = new ArrayList<>();
        for (PlaylistDTO playlistDTO : playlists){
            playlistList.add(convertToModelForUpdate(playlistDTO));
        }
        return playlistList;
    }
}

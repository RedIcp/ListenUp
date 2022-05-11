package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;
import com.listenup.individualassignment.model.User;
import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;

public class CustomerDTOConverter {
    private CustomerDTOConverter(){

    }
    public static UpdateUserDTO convertToDTOForUpdate(User user){
        return UpdateUserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public static ViewUserDTO convertToDTOForView(User user){
        return ViewUserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
    public static Customer convertToModelForUpdate(UpdateUserDTO user){
        return new Customer(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
    public static Customer convertToModelForCreate(CreateUserRequestDTO user){
        return new Customer(user.getUsername(), user.getEmail(), user.getPassword());
    }
    public static CustomerLikedSongListDTO convertToDTOForLikedSong(Customer user){
        return CustomerLikedSongListDTO.builder()
                .id(user.getId())
                .likedSongs(SongDTOConverter.convertToSingleSongDTOList(user.getLikedSongs()))
                .build();
    }
    public static CustomerPlaylistListDTO convertToDTOForPlaylist(Customer user){
        return CustomerPlaylistListDTO.builder()
                .id(user.getId())
                .playlists(PlaylistDTOConverter.convertToDTOList(user.getPlaylists()))
                .build();
    }
    public static CustomerLikedPlaylistListDTO convertToDTOForLikedPlaylist(Customer user){
        return CustomerLikedPlaylistListDTO.builder()
                .id(user.getId())
                .likedPlaylists(PlaylistDTOConverter.convertToDTOList(user.getLikedPlaylists()))
                .build();
    }
    public static List<ViewUserDTO> convertToDTOList(List<User> users){
        List<ViewUserDTO> dtoList = new ArrayList<>();
        for (User user: users){
            dtoList.add(convertToDTOForView(user));
        }
        return dtoList;
    }
}

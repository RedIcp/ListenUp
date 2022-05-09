package com.listenup.individualassignment.business.imp.dtoconverter;

import java.util.List;
import java.util.ArrayList;

import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserDTO;
import com.listenup.individualassignment.model.User;
import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.dto.vieweditdto.UserDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;

public class CustomerDTOConverter {
    private CustomerDTOConverter(){

    }
    public static UserDTO convertToDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public static Customer convertToModelForUpdate(UserDTO user){
        return new Customer(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
    public static Customer convertToModelForCreate(CreateUserDTO user){
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
    public static List<UserDTO> convertToDTOList(List<User> users){
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user: users){
            dtoList.add(convertToDTO(user));
        }
        return dtoList;
    }
}

package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserDTO;
import com.listenup.individualassignment.dto.vieweditdto.UserDTO;

public interface UserService {
    CreateUserDTO createAccount(CreateUserDTO user);
    List<UserDTO> getUsers();
    UserDTO getUser(long id);
    CustomerLikedSongListDTO getCustomerCollection(long id);
    CustomerPlaylistListDTO getCustomerPlaylists(long id);
    CustomerLikedPlaylistListDTO getCustomerLikedPlaylists(long id);
    UserDTO updateAccount(UserDTO user);
    CustomerLikedSongListDTO editUserCollection(CustomerLikedSongListDTO user);
    CustomerLikedPlaylistListDTO editUserLikedPlaylists(CustomerLikedPlaylistListDTO user);
    boolean deleteAccount(long id);
}

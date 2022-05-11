package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;
import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;

public interface UserService {
    CreateUserResponseDTO createAccount(CreateUserRequestDTO user);
    List<ViewUserDTO> getUsers();
    UpdateUserDTO getUser(long id);
    CustomerLikedSongListDTO getCustomerCollection(long id);
    CustomerPlaylistListDTO getCustomerPlaylists(long id);
    CustomerLikedPlaylistListDTO getCustomerLikedPlaylists(long id);
    UpdateUserDTO updateAccount(UpdateUserDTO user);
    CustomerLikedSongListDTO editUserCollection(CustomerLikedSongListDTO user);
    CustomerLikedPlaylistListDTO editUserLikedPlaylists(CustomerLikedPlaylistListDTO user);
    boolean deleteAccount(long id);
}

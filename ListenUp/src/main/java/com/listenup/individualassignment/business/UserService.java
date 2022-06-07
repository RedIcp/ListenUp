package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.dto.*;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;
import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;

public interface UserService {
    CreateUserResponseDTO createAccount(CreateUserRequestDTO user);
    LoginResponseDTO login(LoginRequestDTO request);
    List<ViewUserDTO> getUsers();
    UpdateUserDTO getUser(long id);
    CustomerLikedSongListDTO getCustomerCollection(long id);
    CustomerPlaylistListDTO getCustomerPlaylists(long id);
    CustomerLikedPlaylistListDTO getCustomerLikedPlaylists(long id);
    UpdateUserDTO updateAccount(UpdateUserDTO user);
    void addSongToCollection(AddRemoveSongToCollectionDTO song);
    void removeSongFromCollection(AddRemoveSongToCollectionDTO song);
    void addLikedPlaylist(AddRemoveLikedPlaylistDTO playlist);
    void removeLikedPlaylist(AddRemoveLikedPlaylistDTO playlist);
    boolean deleteAccount(long id);
}

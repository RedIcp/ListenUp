package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.User;
import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.dto.createupdate.UserDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;

public interface UserService {
    //dto
    List<UserDTO> getUserDTOs(List<User> users);
    User userDTOConvertor(UserDTO dto);
    UserDTO userObjConvertorForProfile(User user);
    CustomerPlaylistListDTO customerObjConvertorForPlaylists(Customer user);
    CustomerLikedSongListDTO customerObjConvertorForLikedSongs(Customer user);

    //crud
    boolean createAccount(User user);
    List<User> getUsers();
    User getUserByID(long id);
    boolean updateAccount(User user);
    boolean deleteAccount(long id);

    //auth
    boolean login(User user);
}

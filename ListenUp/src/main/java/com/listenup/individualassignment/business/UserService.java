package com.listenup.individualassignment.business;

import java.util.List;
import com.listenup.individualassignment.model.User;
import com.listenup.individualassignment.dto.CreateUpdate.UserDTO;

public interface UserService {
    //dto
    List<UserDTO> getUserDTOs();
    User userDTOConvertor(UserDTO dto);
    UserDTO userObjConvertor(User user);

    //crud
    boolean createAccount(User user);
    List<User> getUsers();
    User getUserByID(long id);
    boolean updateAccount(User user);
    boolean deleteAccount(long id);

    //auth
    boolean login(User user);
}

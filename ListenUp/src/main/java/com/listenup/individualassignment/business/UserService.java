package com.listenup.individualassignment.business;

import com.listenup.individualassignment.dto.AccountDTO;
import com.listenup.individualassignment.model.User;

import java.util.List;

public interface UserService {
    User userDTOConvertor(AccountDTO dto);
    boolean createAccount(User user);
    boolean login(User user);
    List<User> getUsers();
    User getUserByID(long id);
    boolean deleteAccount(long id);
    boolean updateAccount(User user);

}

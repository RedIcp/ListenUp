package com.listenup.individualassignment.business;

import java.util.List;

import com.listenup.individualassignment.model.User;

public interface UserService {
    boolean createAccount(User user);
    List<User> getUsers();
    User getUserByID(long id);
    boolean updateAccount(User user);
    boolean deleteAccount(long id);
    boolean login(User user);
}

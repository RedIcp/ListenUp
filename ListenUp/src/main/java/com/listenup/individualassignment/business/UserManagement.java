package com.listenup.individualassignment.business;

import com.listenup.individualassignment.model.User;

import java.util.List;

public interface UserManagement {
    boolean createAccount(User user);
    boolean login(User user);
    List<User> getUsers();
    User getUserByID(int id);
    boolean deleteAccount(int id);
    boolean updateAccount(User user);

}

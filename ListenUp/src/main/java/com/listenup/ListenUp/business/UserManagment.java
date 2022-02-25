package com.listenup.ListenUp.business;

import com.listenup.ListenUp.model.User;

import java.util.List;

public interface UserManagment {
    boolean createAccount(User user);
    List<User> getUsers();
    User getUserByID(int id);
    boolean deleteAccount(int id);
    boolean updateAccount(User user);

}

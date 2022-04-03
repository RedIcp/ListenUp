package com.listenup.individualassignment.persistence;

import com.listenup.individualassignment.model.User;

import java.util.List;

public interface DBUser {
    boolean addUser(int id, String userName, String email, String password);
    List<User> getUsers();
    boolean editUser(int id, String userName, String email, String password);
    boolean deleteUser(int id);
}

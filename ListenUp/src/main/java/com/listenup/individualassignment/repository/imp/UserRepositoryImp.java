package com.listenup.individualassignment.repository.imp;

import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.repository.UserRepository;
import com.listenup.individualassignment.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImp implements UserRepository {

    private  List<User> users;

    public UserRepositoryImp(){
        users = new ArrayList<>();

        users.add(new Customer(1,"Yellow","yellow@gmail.com", "123Yellow"));
        users.add(new Customer(2,"Blue","blue@gmail.com", "123Blue"));
        users.add(new Customer(3,"Red","red@gmail.com", "123Red"));
        users.add(new Customer(4,"Purple","purple@gmail.com", "123Purple"));
        users.add(new Customer(5,"Black","black@gmail.com", "123Black"));
        users.add(new Customer(6,"White","white@gmail.com", "123White"));

    }

    public boolean addUser(int id, String userName, String email, String password){
        return  true;
    }
    public List<User> getUsers(){
        return  users;
    }
    public boolean editUser(int id, String userName, String email, String password){
        return true;
    }
    public boolean deleteUser(int id){
        return  true;
    }
}

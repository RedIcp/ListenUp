package com.listenup.ListenUp.persistence.imp;

import com.listenup.ListenUp.model.Customer;
import com.listenup.ListenUp.persistence.DBUser;
import com.listenup.ListenUp.model.User;

import java.util.ArrayList;
import java.util.List;

public class DBUserImp implements DBUser {

    private  List<User> users;

    public DBUserImp(){
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

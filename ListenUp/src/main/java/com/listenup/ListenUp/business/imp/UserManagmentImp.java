package com.listenup.ListenUp.business.imp;

import com.listenup.ListenUp.business.UserManagment;
import com.listenup.ListenUp.model.User;
import com.listenup.ListenUp.persistence.DBUser;

import java.util.List;

public class UserManagmentImp implements UserManagment {

    private DBUser db;

    public UserManagmentImp(DBUser db){
        this.db = db;
    }
    public List<User> getUsers(){
        return db.getUsers();
    }
    public User getUserByID(int id){
        for(User user: getUsers()){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
    public User getUserByEmail(String email){
        for (User user: getUsers()){
            if(user.getEmail()==email){
                return user;
            }
        }
        return null;
    }
    public boolean userByIdExist(int id){
        if(getUserByID(id)!=null){
            return true;
        }
        return false;
    }
    public boolean userByEmailExist(String email){
        if(getUserByEmail(email)!=null){
            return true;
        }
        return false;
    }
    public boolean createAccount(User user){
        if(userByEmailExist(user.getEmail()) == false && userByIdExist(user.getId()) == false){
            getUsers().add(user);
            return true;
        }
        return false;
    }
    public boolean deleteAccount(int id){
        if(userByIdExist(id) == true){
            getUsers().remove(getUserByID(id));
            return true;
        }
        return false;
    }
    public boolean updateAccount(User user){
        User old = getUserByID(user.getId());
        if(old==null){
            return false;
        }
        if(userByEmailExist(user.getEmail())==true){
            return  false;
        }
        old.setEmail(user.getEmail());
        old.setPassword(user.getPassword());
        old.setUserName(user.getUserName());
        return true;
    }
}

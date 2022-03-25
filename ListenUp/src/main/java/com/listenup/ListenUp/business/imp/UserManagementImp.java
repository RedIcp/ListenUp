package com.listenup.ListenUp.business.imp;

import com.listenup.ListenUp.business.UserManagement;
import com.listenup.ListenUp.model.User;
import com.listenup.ListenUp.persistence.DBUser;

import java.util.List;

public class UserManagementImp implements UserManagement {

    private DBUser db;

    public UserManagementImp(DBUser db){
        this.db = db;
    }
    public List<User> getUsers(){
        return db.getUsers();
    }

    public boolean createAccount(User user){
        if(userByEmailExist(user.getEmail()) == false && userByIdExist(user.getId()) == false){
            getUsers().add(user);
            db.addUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
            return true;
        }
        return false;
    }
    public boolean deleteAccount(int id){
        if(userByIdExist(id) == true){
            getUsers().remove(getUserByID(id));
            db.deleteUser(id);
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
        old.setUsername(user.getUsername());
        db.editUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        return true;
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
}

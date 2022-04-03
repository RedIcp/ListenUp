package com.listenup.individualassignment.business.imp;

import com.listenup.individualassignment.business.UserManagement;
import com.listenup.individualassignment.model.User;
import com.listenup.individualassignment.persistence.DBUser;

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
        boolean result = false;
        if(!userByEmailExist(user.getEmail()) && !userByIdExist(user.getId())){
            getUsers().add(user);
            db.addUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
            result = true;
        }
        return result;
    }
    public boolean login(User user){
        boolean result = false;
        if(getUserForLogin(user.getEmail(), user.getPassword()) != null){
            result = true;
        }
        return result;
    }
    public boolean deleteAccount(int id){
        boolean result = false;
        if(userByIdExist(id)){
            getUsers().remove(getUserByID(id));
            db.deleteUser(id);
            result = true;
        }
        return result;
    }
    public boolean updateAccount(User user){
        User old = getUserByID(user.getId());
        boolean result = false;
        if(old!=null){
            if(user.getEmail().equals(old.getEmail())){
                old.setEmail(user.getEmail());
                old.setPassword(user.getPassword());
                old.setUsername(user.getUsername());
                db.editUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
                result = true;
            }
            else {
                if(!userByEmailExist(user.getEmail())){
                    old.setEmail(user.getEmail());
                    old.setPassword(user.getPassword());
                    old.setUsername(user.getUsername());
                    db.editUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
                    result = true;
                }
            }
        }
        return result;
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
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
    public User getUserForLogin(String email, String password){
        for (User user: getUsers()){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public boolean userByIdExist(int id){
        boolean result = false;
        if(getUserByID(id)!=null){
            result = true;
        }
        return result;
    }
    public boolean userByEmailExist(String email){
        boolean result = false;
        if(getUserByEmail(email)!=null){
            result = true;
        }
        return result;
    }
}

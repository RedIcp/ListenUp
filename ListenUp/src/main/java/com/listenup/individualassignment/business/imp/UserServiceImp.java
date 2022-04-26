package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.model.User;
import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository db;

    public boolean createAccount(User user){
        boolean result = false;
        if(!userByEmailExist(user.getEmail()) && !userByIdExist(user.getId())){
            getUsers().add(user);
            db.save(user);
            result = true;
        }
        return result;
    }

    public List<User> getUsers(){
        return db.findAll();
    }

    public boolean updateAccount(User user){
        User old = getUserByID(user.getId());
        boolean result = false;
        if(old!=null){
            if(user.getEmail().equals(old.getEmail())){
                old.setEmail(user.getEmail());
                old.setPassword(user.getPassword());
                old.setUsername(user.getUsername());
                db.save(old);
                result = true;
            }
            else {
                if(!userByEmailExist(user.getEmail())){
                    old.setEmail(user.getEmail());
                    old.setPassword(user.getPassword());
                    old.setUsername(user.getUsername());
                    db.save(old);
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean deleteAccount(long id){
        boolean result = false;
        if(userByIdExist(id)){
            getUsers().remove(getUserByID(id));
            db.delete(getUserByID(id));
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
    public User getUserByID(long id){
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
    public boolean userByIdExist(long id){
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

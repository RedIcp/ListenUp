package com.listenup.individualassignment.business.imp;

import java.util.List;

import com.listenup.individualassignment.business.exception.InvalidCustomerEmailException;
import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.imp.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserDTO;
import com.listenup.individualassignment.dto.vieweditdto.UserDTO;
import com.listenup.individualassignment.model.Customer;
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

    String error = "INVALID_ID";

    public CreateUserDTO createAccount(CreateUserDTO user){
        if(db.existsByEmail(user.getEmail())){
            throw new InvalidCustomerEmailException("EMAIL_EXIST");
        }
        db.save(CustomerDTOConverter.convertToModelForCreate(user));
        return user;
    }

    public List<UserDTO> getUsers(){
        return CustomerDTOConverter.convertToDTOList(db.findAll());
    }
    public UserDTO getUser(long id){ return CustomerDTOConverter.convertToDTO(db.getById(id)); }
    public CustomerLikedSongListDTO getCustomerCollection(long id){
        return CustomerDTOConverter.convertToDTOForLikedSong((Customer) db.getById(id));
    }
    public CustomerPlaylistListDTO getCustomerPlaylists(long id){
        return CustomerDTOConverter.convertToDTOForPlaylist((Customer) db.getById(id));
    }
    public CustomerLikedPlaylistListDTO getCustomerLikedPlaylists(long id){
        return CustomerDTOConverter.convertToDTOForLikedPlaylist((Customer) db.getById(id));
    }

    public UserDTO updateAccount(UserDTO user){
        User old = db.getById(user.getId());
        if(!db.existsById(user.getId())){
            throw new InvalidCustomerException(error);
        }
        if(user.getEmail().equals(old.getEmail())){
            db.save(CustomerDTOConverter.convertToModelForUpdate(user));
        }
        else {
            if(db.existsByEmail(user.getEmail())){
                throw new InvalidCustomerEmailException("EMAIL_EXIST");
            }
            db.save(CustomerDTOConverter.convertToModelForUpdate(user));
        }
        return user;
    }
    public CustomerLikedSongListDTO editUserCollection(CustomerLikedSongListDTO user){
        Customer old = (Customer) db.getById(user.getId());
        if(!db.existsById(user.getId())){
            throw new InvalidCustomerException(error);
        }
        old.setLikedSongs(SongDTOConverter.convertToSingleSongModelList(user.getLikedSongs()));
        db.save(old);
        return user;
    }
    public CustomerLikedPlaylistListDTO editUserLikedPlaylists(CustomerLikedPlaylistListDTO user){
        Customer old = (Customer) db.getById(user.getId());
        if(!db.existsById(user.getId())){
            throw new InvalidCustomerException(error);
        }
        old.setLikedPlaylists(PlaylistDTOConverter.convertToModelList(user.getLikedPlaylists()));
        db.save(old);
        return user;
    }

    public boolean deleteAccount(long id){
        boolean result = false;
        if(db.existsById(id)){
            db.deleteById(id);
            result = true;
        }
        return result;
    }
}

package com.listenup.individualassignment.business.imp;

import java.util.List;
import java.util.Set;

import com.listenup.individualassignment.business.AccessTokenEncoder;
import com.listenup.individualassignment.business.exception.InvalidCredentialsException;
import com.listenup.individualassignment.business.exception.InvalidCustomerEmailException;
import com.listenup.individualassignment.business.exception.InvalidCustomerException;
import com.listenup.individualassignment.business.exception.UnauthorizedDataAccessException;
import com.listenup.individualassignment.business.imp.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.SongDTOConverter;
import com.listenup.individualassignment.dto.*;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;
import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;
import com.listenup.individualassignment.entity.Customer;
import com.listenup.individualassignment.entity.RoleEnum;
import com.listenup.individualassignment.entity.User;
import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.entity.UserRole;
import com.listenup.individualassignment.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository db;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    private final AccessTokenDTO requestAccessToken;

    String error = "INVALID_ID";

    public CreateUserResponseDTO createAccount(CreateUserRequestDTO user){
        if(db.existsByEmail(user.getEmail())){
            throw new InvalidCustomerEmailException("EMAIL_EXIST");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        User newUser = CustomerDTOConverter.convertToModelForCreate(user);

        newUser.setPassword(encodedPassword);
        newUser.setUserRoles(Set.of(
                UserRole.builder()
                        .user(newUser)
                        .role(RoleEnum.CUSTOMER)
                        .build()

        ));

        User savedUser = db.save(newUser);

        return CreateUserResponseDTO.builder()
                .userID(savedUser.getId())
                .build();
    }

    public LoginResponseDTO login(LoginRequestDTO request){
        if(!db.existsByEmail(request.getEmail())){
            throw new InvalidCustomerEmailException("EMAIL_DOES_NOT_EXIST");
        }
        User user = db.getByEmail(request.getEmail());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);

        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }

    public String generateAccessToken(User user) {
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                AccessTokenDTO.builder()
                        .subject(user.getEmail())
                        .roles(roles)
                        .userID(user.getId())
                        .build());
    }

    public List<ViewUserDTO> getUsers(){
        return CustomerDTOConverter.convertToDTOList(db.findAll());
    }
    public UpdateUserDTO getUser(long id){
        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name()) && requestAccessToken.getUserID() != id) {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }
        return CustomerDTOConverter.convertToDTOForUpdate(db.getById(id));
    }
    public CustomerLikedSongListDTO getCustomerCollection(long id){
        return CustomerDTOConverter.convertToDTOForLikedSong((Customer) db.getById(id));
    }
    public CustomerPlaylistListDTO getCustomerPlaylists(long id){
        return CustomerDTOConverter.convertToDTOForPlaylist((Customer) db.getById(id));
    }
    public CustomerLikedPlaylistListDTO getCustomerLikedPlaylists(long id){
        return CustomerDTOConverter.convertToDTOForLikedPlaylist((Customer) db.getById(id));
    }

    public UpdateUserDTO updateAccount(UpdateUserDTO user){
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

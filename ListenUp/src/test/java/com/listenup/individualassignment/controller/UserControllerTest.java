package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;
import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    void getAllUsers() throws Exception{
        ViewUserDTO user = ViewUserDTO.builder()
                .id(1l)
                .username("Blue")
                .email("blue@gmail.com")
                .build();

        List<ViewUserDTO> users = new ArrayList<>();
        users.add(user);

        when(service.getUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [
                                {
                                        "id": 1,
                                        "username": "Blue",
                                        "email": "blue@gmail.com"       
                                }
                            ]                          
                        """));

        verify(service).getUsers();
    }

    @Test
    void getAllUsersNotFound() throws Exception{
        when(service.getUsers()).thenReturn(null);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getUsers();
    }

    @Test
    void getUserPath() throws Exception{
        UpdateUserDTO user = UpdateUserDTO.builder()
                .id(1l)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        when(service.getUser(1l)).thenReturn(user);

        mockMvc.perform(get("/users/1/profile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                        "id": 1,
                                        "username": "Blue",
                                        "email": "blue@gmail.com",
                                        "password": "123Blue"                   
                            }                       
                        """));

        verify(service).getUser(1l);
    }

    @Test
    void getUserPathNotFound() throws Exception{
        when(service.getUser(1l)).thenReturn(null);

        mockMvc.perform(get("/users/1/profile"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getUser(1l);
    }

    @Test
    void getCustomerPlaylistsPath() throws Exception{
        CustomerPlaylistListDTO user = CustomerPlaylistListDTO.builder()
                .id(1l)
                .playlists(Collections.emptyList())
                .build();

        when(service.getCustomerPlaylists(1l)).thenReturn(user);

        mockMvc.perform(get("/users/1/playlists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                        "id": 1,
                                        "playlists": []                 
                            }                      
                        """));

        verify(service).getCustomerPlaylists(1l);
    }

    @Test
    void getCustomerPlaylistsPathNotFound() throws Exception{
        when(service.getCustomerPlaylists(1l)).thenReturn(null);

        mockMvc.perform(get("/users/1/playlists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getCustomerPlaylists(1l);
    }

    @Test
    void getCustomerLikedSongsPath() throws Exception{
        CustomerLikedSongListDTO user = CustomerLikedSongListDTO.builder()
                .id(1l)
                .likedSongs(Collections.emptyList())
                .build();

        when(service.getCustomerCollection(1l)).thenReturn(user);

        mockMvc.perform(get("/users/1/collection"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                        "id": 1,
                                        "likedSongs": []                 
                            }                      
                        """));

        verify(service).getCustomerCollection(1l);
    }

    @Test
    void getCustomerLikedSongsPathNotFound() throws Exception{
        when(service.getCustomerCollection(1l)).thenReturn(null);

        mockMvc.perform(get("/users/1/collection"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getCustomerCollection(1l);
    }

    @Test
    void getCustomerLikedPlaylistsPath() throws Exception{
        CustomerLikedPlaylistListDTO user = CustomerLikedPlaylistListDTO.builder()
                .id(1l)
                .likedPlaylists(Collections.emptyList())
                .build();

        when(service.getCustomerLikedPlaylists(1l)).thenReturn(user);

        mockMvc.perform(get("/users/1/likedplaylists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                        "id": 1,
                                        "likedPlaylists": []                 
                            }                      
                        """));

        verify(service).getCustomerLikedPlaylists(1l);
    }

    @Test
    void getCustomerLikedPlaylistsPathNotFound() throws Exception{
        when(service.getCustomerLikedPlaylists(1l)).thenReturn(null);

        mockMvc.perform(get("/users/1/likedplaylists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getCustomerLikedPlaylists(1l);
    }

    @Test
    void createUser() throws Exception{
        CreateUserRequestDTO user = CreateUserRequestDTO.builder()
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        CreateUserResponseDTO response = CreateUserResponseDTO.builder()
                .userID(1l)
                .build();

        when(service.createAccount(user)).thenReturn(response);

        mockMvc.perform(post("/users/signup")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "username": "Yellow",
                                        "email": "yellow@gmail.com",
                                        "password": "123Yellow"                   
                            }  
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {
                                        "userID": 1               
                            }  
                        """));

        verify(service).createAccount(user);
    }

    @Test
    void updateUser() throws Exception{
        mockMvc.perform(put("/users/1/profile")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "username": "Yellow",
                                        "email": "yellow@gmail.com",
                                        "password": "123Yellow"                   
                                } 
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdateUserDTO user = UpdateUserDTO.builder()
                .id(1l)
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        verify(service).updateAccount(user);
    }

    @Test
    void addSongToCollection() throws Exception{
        mockMvc.perform(put("/users/1/collection")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "likedSongs": []                 
                                } 
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        CustomerLikedSongListDTO user = CustomerLikedSongListDTO.builder()
                .id(1l)
                .likedSongs(Collections.emptyList())
                .build();

        verify(service).editUserCollection(user);
    }

    @Test
    void addLikedPlaylist() throws Exception{
        mockMvc.perform(put("/users/1/likedplaylist")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "likedPlaylists": []                       
                                } 
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        CustomerLikedPlaylistListDTO user = CustomerLikedPlaylistListDTO.builder()
                .id(1l)
                .likedPlaylists(Collections.emptyList())
                .build();

        verify(service).editUserLikedPlaylists(user);
    }

    @Test
    void deleteUser() throws Exception{
        mockMvc.perform(delete("/users/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).deleteAccount(1L);
    }
}
package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveLikedPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToCollectionDTO;
import com.listenup.individualassignment.dto.vieweditdto.UpdateUserDTO;
import com.listenup.individualassignment.dto.vieweditdto.ViewUserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
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
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllUsersNotFound() throws Exception{
        when(service.getUsers()).thenReturn(null);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getUsers();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
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
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getUserPathNotFound() throws Exception{
        when(service.getUser(1l)).thenReturn(null);

        mockMvc.perform(get("/users/1/profile"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getUser(1l);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
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
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getCustomerPlaylistsPathNotFound() throws Exception{
        when(service.getCustomerPlaylists(1l)).thenReturn(null);

        mockMvc.perform(get("/users/1/playlists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getCustomerPlaylists(1l);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
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
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getCustomerLikedSongsPathNotFound() throws Exception{
        when(service.getCustomerCollection(1l)).thenReturn(null);

        mockMvc.perform(get("/users/1/collection"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getCustomerCollection(1l);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
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
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getCustomerLikedPlaylistsPathNotFound() throws Exception{
        when(service.getCustomerLikedPlaylists(1l)).thenReturn(null);

        mockMvc.perform(get("/users/1/likedplaylists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getCustomerLikedPlaylists(1l);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
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
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void addSongToCollection() throws Exception{
        mockMvc.perform(put("/users/1/collection/add")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "song": null                 
                                } 
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AddRemoveSongToCollectionDTO user = AddRemoveSongToCollectionDTO.builder()
                .customerID(1l)
                .song(null)
                .build();

        verify(service).addSongToCollection(user);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void removeSongToCollection() throws Exception{
        mockMvc.perform(put("/users/1/collection/remove")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "song": null                 
                                } 
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AddRemoveSongToCollectionDTO user = AddRemoveSongToCollectionDTO.builder()
                .customerID(1l)
                .song(null)
                .build();

        verify(service).removeSongFromCollection(user);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void addLikedPlaylist() throws Exception{
        mockMvc.perform(put("/users/1/likedplaylist/add")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "playlist": null                       
                                } 
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AddRemoveLikedPlaylistDTO user = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1l)
                .playlist(null)
                .build();

        verify(service).addLikedPlaylist(user);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void removeLikedPlaylist() throws Exception{
        mockMvc.perform(put("/users/1/likedplaylist/remove")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "playlist": null                       
                                } 
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AddRemoveLikedPlaylistDTO user = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1l)
                .playlist(null)
                .build();

        verify(service).removeLikedPlaylist(user);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void deleteUser() throws Exception{
        mockMvc.perform(delete("/users/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).deleteAccount(1L);
    }
}
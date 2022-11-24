package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.user.account.DeleteAccountUseCase;
import com.listenup.individualassignment.business.user.account.GetUserUseCase;
import com.listenup.individualassignment.business.user.account.UpdateProfileUseCase;
import com.listenup.individualassignment.business.user.action.*;
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
    private UpdateProfileUseCase updateProfileUseCase;
    @MockBean
    private DeleteAccountUseCase deleteAccountUseCase;
    @MockBean
    private GetUserUseCase getUserUseCase;
    @MockBean
    private GetUsersUseCase getUsersUseCase;
    @MockBean
    private GetUserPlaylistsUseCase getCustomerPlaylistsUseCase;
    @MockBean
    private GetUserLikedPlaylistsUseCase getUserLikedPlaylistsUseCase;
    @MockBean
    private GetUserLikedSongsUseCase getUserLikedSongsUseCase;
    @MockBean
    private LikeUnlikeSongUseCase likeUnlikeSongUseCase;
    @MockBean
    private LikeUnlikePlaylistUseCase likeUnlikePlaylistUseCase;

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllUsers() throws Exception{
        ViewUserDTO user = ViewUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("blue@gmail.com")
                .build();

        List<ViewUserDTO> users = new ArrayList<>();
        users.add(user);

        when(getUsersUseCase.getUsers()).thenReturn(users);

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

        verify(getUsersUseCase).getUsers();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllUsersNotFound() throws Exception{
        when(getUsersUseCase.getUsers()).thenReturn(null);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getUsersUseCase).getUsers();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getUserPath() throws Exception{
        UpdateUserDTO user = UpdateUserDTO.builder()
                .id(1L)
                .username("Blue")
                .email("blue@gmail.com")
                .password("123Blue")
                .build();

        when(getUserUseCase.getUser(1L)).thenReturn(user);

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

        verify(getUserUseCase).getUser(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getUserPathNotFound() throws Exception{
        when(getUserUseCase.getUser(1L)).thenReturn(null);

        mockMvc.perform(get("/users/1/profile"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getUserUseCase).getUser(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getCustomerPlaylistsPath() throws Exception{
        CustomerPlaylistListDTO user = CustomerPlaylistListDTO.builder()
                .id(1L)
                .playlists(Collections.emptyList())
                .build();

        when(getCustomerPlaylistsUseCase.getCustomerPlaylists(1L)).thenReturn(user);

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

        verify(getCustomerPlaylistsUseCase).getCustomerPlaylists(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getCustomerPlaylistsPathNotFound() throws Exception{
        when(getCustomerPlaylistsUseCase.getCustomerPlaylists(1L)).thenReturn(null);

        mockMvc.perform(get("/users/1/playlists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getCustomerPlaylistsUseCase).getCustomerPlaylists(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getCustomerLikedSongsPath() throws Exception{
        CustomerLikedSongListDTO user = CustomerLikedSongListDTO.builder()
                .id(1L)
                .songs(Collections.emptyList())
                .build();

        when(getUserLikedSongsUseCase.getCustomerCollection(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1/collection"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                        "id": 1,
                                        "songs": []
                            }
                        """));

        verify(getUserLikedSongsUseCase).getCustomerCollection(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getCustomerLikedSongsPathNotFound() throws Exception{
        when(getUserLikedSongsUseCase.getCustomerCollection(1L)).thenReturn(null);

        mockMvc.perform(get("/users/1/collection"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getUserLikedSongsUseCase).getCustomerCollection(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getCustomerLikedPlaylistsPath() throws Exception{
        CustomerLikedPlaylistListDTO user = CustomerLikedPlaylistListDTO.builder()
                .id(1L)
                .playlists(Collections.emptyList())
                .build();

        when(getUserLikedPlaylistsUseCase.getCustomerLikedPlaylists(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1/likedplaylists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                        "id": 1,
                                        "playlists": []
                            }
                        """));

        verify(getUserLikedPlaylistsUseCase).getCustomerLikedPlaylists(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getCustomerLikedPlaylistsPathNotFound() throws Exception{
        when(getUserLikedPlaylistsUseCase.getCustomerLikedPlaylists(1L)).thenReturn(null);

        mockMvc.perform(get("/users/1/likedplaylists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getUserLikedPlaylistsUseCase).getCustomerLikedPlaylists(1L);
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
                .id(1L)
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        verify(updateProfileUseCase).updateAccount(user);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void addSongToCollection() throws Exception{
        mockMvc.perform(put("/users/1/collection")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "customerID": 1,
                                        "songID": 1
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AddRemoveSongToCollectionDTO user = AddRemoveSongToCollectionDTO.builder()
                .customerID(1L)
                .songID(1L)
                .build();

        verify(likeUnlikeSongUseCase).likeUnlikeSong(user);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void addLikedPlaylist() throws Exception{
        mockMvc.perform(put("/users/1/library")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "customerID": 1,
                                        "playlistID": 1
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AddRemoveLikedPlaylistDTO user = AddRemoveLikedPlaylistDTO.builder()
                .customerID(1L)
                .playlistID(1L)
                .build();

        verify(likeUnlikePlaylistUseCase).likeUnlikePlaylist(user);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void deleteUser() throws Exception{
        mockMvc.perform(delete("/users/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(deleteAccountUseCase).deleteAccount(1L);
    }
}
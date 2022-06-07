package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.business.imp.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.entity.Customer;
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
class PlaylistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistService service;

    Customer customer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void getAllPlaylists() throws Exception{
        PlaylistDTO playlist1 = PlaylistDTO.builder()
                .id(1l)
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();
        PlaylistDTO playlist2 = PlaylistDTO.builder()
                .id(2l)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        List<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(playlist1);
        playlists.add(playlist2);

        when(service.getPlaylists()).thenReturn(playlists);

        mockMvc.perform(get("/playlists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [
                                {
                                    "id": 1,
                                    "name": "Chill",
                                    "customer": {
                                        "id": 1,
                                        "username": "Yellow",
                                        "email": "yellow@gmail.com",
                                        "password": "123Yellow" 
                                    }                   
                                },
                                {
                                    "id": 2,
                                    "name": "Workout",
                                    "customer": {
                                        "id": 1,
                                        "username": "Yellow",
                                        "email": "yellow@gmail.com",
                                        "password": "123Yellow" 
                                    }  
                                }
                            ]                          
                        """));

        verify(service).getPlaylists();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void getAllPlaylistsNotFound() throws Exception {
        when(service.getPlaylists()).thenReturn(null);

        mockMvc.perform(get("/playlists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getPlaylists();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void getPlaylistPath() throws Exception{
        PlaylistSongListDTO playlist = PlaylistSongListDTO.builder()
                .id(1l)
                .name("Chill")
                .songs(Collections.emptyList())
                .build();

        when(service.getPlaylistSong(1l)).thenReturn(playlist);

        mockMvc.perform(get("/playlists/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                       "id": 1,
                                       "name": "Chill",
                                       "songs": []
                            }                    
                        """));

        verify(service).getPlaylistSong(1l);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void getPlaylistPathNotFound() throws Exception{
        when(service.getPlaylistSong(1l)).thenReturn(null);

        mockMvc.perform(get("/playlists/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getPlaylistSong(1l);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void createPlaylist() throws Exception{
        CreatePlaylistRequestDTO playlist = CreatePlaylistRequestDTO.builder()
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        CreatePlaylistResponseDTO response = CreatePlaylistResponseDTO.builder()
                .playlistID(1l)
                .build();

        when(service.addPlaylist(playlist)).thenReturn(response);

        mockMvc.perform(post("/playlists")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "name": "Chill",
                                        "customer": {
                                             "id": 1,
                                             "username": "Yellow",
                                             "email": "yellow@gmail.com",
                                             "password": "123Yellow" 
                                    }  
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {
                                        "playlistID": 1  
                            }
                        """));

        verify(service).addPlaylist(playlist);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void addSongToPlaylist() throws Exception{
        mockMvc.perform(put("/playlists/1/songs")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "song": null  
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AddRemoveSongToPlaylistDTO playlist = AddRemoveSongToPlaylistDTO.builder()
                .playlistID(1l)
                .song(null)
                .build();

        verify(service).addSongToPlaylist(playlist);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void updatePlaylist() throws Exception{
        mockMvc.perform(put("/playlists/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "name": "Chill",
                                        "customer": {
                                             "id": 1,
                                             "username": "Yellow",
                                             "email": "yellow@gmail.com",
                                             "password": "123Yellow" 
                                    }  
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        PlaylistDTO playlist = PlaylistDTO.builder()
                .id(1l)
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        verify(service).editPlaylist(playlist);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void deletePlaylist() throws Exception{
        mockMvc.perform(delete("/playlists/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).deletePlaylist(1L);
    }
}
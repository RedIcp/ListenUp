package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.business.imp.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.model.Playlist;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlaylistController.class)
class PlaylistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistService service;

    Customer customer = new Customer(1l,"Yellow", "yellow@gmail.com", "123Yellow");

    @Test
    void getAllPlaylists() throws Exception{
        PlaylistDTO playlist1 = PlaylistDTO.builder()
                .id(1l)
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTO(customer))
                .build();
        PlaylistDTO playlist2 = PlaylistDTO.builder()
                .id(2l)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTO(customer))
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
    void getAllPlaylistsNotFound() throws Exception {
        when(service.getPlaylists()).thenReturn(null);

        mockMvc.perform(get("/playlists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getPlaylists();
    }

    @Test
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
    void getPlaylistPathNotFound() throws Exception{
        when(service.getPlaylistSong(1l)).thenReturn(null);

        mockMvc.perform(get("/playlists/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getPlaylistSong(1l);
    }

    @Test
    void createPlaylist() throws Exception{
        CreatePlaylistDTO playlist = CreatePlaylistDTO.builder()
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTO(customer))
                .build();

        when(service.addPlaylist(playlist)).thenReturn(playlist);

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
                                        "name": "Chill",
                                        "customer": {
                                            "id": 1,
                                            "username": "Yellow",
                                            "email": "yellow@gmail.com",
                                            "password": "123Yellow" 
                                    }  
                            }
                        """));

        verify(service).addPlaylist(playlist);
    }

    @Test
    void addSongToPlaylist() throws Exception{
        mockMvc.perform(put("/playlists/1/songs")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "name": "Chill",
                                        "songs": []  
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        PlaylistSongListDTO playlist = PlaylistSongListDTO.builder()
                .id(1l)
                .name("Chill")
                .songs(Collections.emptyList())
                .build();

        verify(service).editPlaylistSongs(playlist);
    }

    @Test
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
                .customer(CustomerDTOConverter.convertToDTO(customer))
                .build();

        verify(service).editPlaylist(playlist);
    }

    @Test
    void deletePlaylist() throws Exception{
        mockMvc.perform(delete("/playlists/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).deletePlaylist(1L);
    }
}
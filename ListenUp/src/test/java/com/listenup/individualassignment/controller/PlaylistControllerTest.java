package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.playlist.*;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.AddRemoveSongToPlaylistDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.repository.entity.Customer;
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
    private CreatePlaylistUseCase createPlaylistUseCase;
    @MockBean
    private GetPlaylistsUseCase getPlaylistsUseCase;
    @MockBean
    private GetPlaylistSongsUseCase getPlaylistSongsUseCase;
    @MockBean
    private DeletePlaylistUseCase deletePlaylistUseCase;
    @MockBean
    private UpdatePlaylistUseCase updatePlaylistUseCase;
    @MockBean
    private AddSongToPlaylistUseCase addSongToPlaylistUseCase;
    @MockBean
    private RemoveSongFromPlaylistUseCase removeSongFromPlaylistUseCase;

    final Customer customer = new Customer(1L,"Yellow", "yellow@gmail.com", "123Yellow");

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void getAllPlaylists() throws Exception{
        PlaylistDTO playlist1 = PlaylistDTO.builder()
                .id(1L)
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();
        PlaylistDTO playlist2 = PlaylistDTO.builder()
                .id(2L)
                .name("Workout")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        List<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(playlist1);
        playlists.add(playlist2);

        when(getPlaylistsUseCase.getPlaylists()).thenReturn(playlists);

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

        verify(getPlaylistsUseCase).getPlaylists();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void getAllPlaylistsNotFound() throws Exception {
        when(getPlaylistsUseCase.getPlaylists()).thenReturn(null);

        mockMvc.perform(get("/playlists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getPlaylistsUseCase).getPlaylists();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void getPlaylistPath() throws Exception{
        PlaylistSongListDTO playlist = PlaylistSongListDTO.builder()
                .id(1L)
                .customer("Yellow")
                .name("Chill")
                .songs(Collections.emptyList())
                .build();

        when(getPlaylistSongsUseCase.getPlaylistSongs(1L)).thenReturn(playlist);

        mockMvc.perform(get("/playlists/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                       "id": 1,
                                       "customer": "Yellow",
                                       "name": "Chill",
                                       "songs": []
                            }
                        """));

        verify(getPlaylistSongsUseCase).getPlaylistSongs(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void getPlaylistPathNotFound() throws Exception{
        when(getPlaylistSongsUseCase.getPlaylistSongs(1L)).thenReturn(null);

        mockMvc.perform(get("/playlists/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getPlaylistSongsUseCase).getPlaylistSongs(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void createPlaylist() throws Exception{
        CreatePlaylistRequestDTO playlist = CreatePlaylistRequestDTO.builder()
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        CreatePlaylistResponseDTO response = CreatePlaylistResponseDTO.builder()
                .playlistID(1L)
                .build();

        when(createPlaylistUseCase.addPlaylist(playlist)).thenReturn(response);

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

        verify(createPlaylistUseCase).addPlaylist(playlist);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void addSongToPlaylist() throws Exception{
        mockMvc.perform(put("/playlists/1/songs/add")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "playlistID": 1,
                                        "songID": 1
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AddRemoveSongToPlaylistDTO playlist = AddRemoveSongToPlaylistDTO.builder()
                .playlistID(1L)
                .songID(1L)
                .build();

        verify(addSongToPlaylistUseCase).addSongToPlaylist(playlist);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void removeSongFromPlaylist() throws Exception{
        mockMvc.perform(put("/playlists/1/songs/remove")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "playlistID": 1,
                                        "songID": 1
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AddRemoveSongToPlaylistDTO playlist = AddRemoveSongToPlaylistDTO.builder()
                .playlistID(1L)
                .songID(1L)
                .build();

        verify(removeSongFromPlaylistUseCase).removeSongFromPlaylist(playlist);
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
                .id(1L)
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        verify(updatePlaylistUseCase).editPlaylist(playlist);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"CUSTOMER"})
    void deletePlaylist() throws Exception{
        mockMvc.perform(delete("/playlists/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(deletePlaylistUseCase).deletePlaylist(1L);
    }
}
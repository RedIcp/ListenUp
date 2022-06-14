package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.ArtistService;
import com.listenup.individualassignment.dto.ArtistAlbumListDTO;
import com.listenup.individualassignment.dto.ArtistSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateArtistResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
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
class ArtistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistService service;

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllArtists() throws Exception {
        ArtistDTO artist1 = ArtistDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .build();
        ArtistDTO artist2 = ArtistDTO.builder()
                .id(2L)
                .name("Post Malone")
                .build();

        List<ArtistDTO> artists = new ArrayList<>();
        artists.add(artist1);
        artists.add(artist2);

        when(service.getArtists()).thenReturn(artists);

        mockMvc.perform(get("/artists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [
                                {
                                    "id": 1,
                                    "name": "Maroon 5"                  
                                },
                                {
                                    "id": 2,
                                    "name": "Post Malone"  
                                }
                            ]                          
                        """));

        verify(service).getArtists();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllArtistsNotFound() throws Exception {
        when(service.getArtists()).thenReturn(null);

        mockMvc.perform(get("/artists"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getArtists();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getArtistPath() throws Exception{
        ArtistSongListDTO artist = ArtistSongListDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .songs(Collections.emptyList())
                .build();

        when(service.getArtistSongs(1L)).thenReturn(artist);

        mockMvc.perform(get("/artists/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                       "id": 1,
                                       "name": "Maroon 5",
                                       "songs": []
                            }                    
                        """));

        verify(service).getArtistSongs(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getArtistPathNotFound() throws Exception{
        when(service.getArtistSongs(1L)).thenReturn(null);

        mockMvc.perform(get("/artists/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getArtistSongs(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getArtistAlbumsPath() throws Exception{
        ArtistAlbumListDTO artist = ArtistAlbumListDTO.builder()
                .id(1L)
                .name("Maroon 5")
                .albums(Collections.emptyList())
                .build();

        when(service.getArtistAlbums(1L)).thenReturn(artist);

        mockMvc.perform(get("/artists/1/albums"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                       "id": 1,
                                       "name": "Maroon 5",
                                       "albums": []
                            }                    
                        """));

        verify(service).getArtistAlbums(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getArtistAlbumsPathNotFound() throws Exception{
        when(service.getArtistAlbums(1L)).thenReturn(null);

        mockMvc.perform(get("/artists/1/albums"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getArtistAlbums(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void createArtist() throws Exception{
        CreateArtistRequestDTO artist = CreateArtistRequestDTO.builder()
                .name("Maroon 5")
                .build();

        CreateArtistResponseDTO response = CreateArtistResponseDTO.builder()
                .artistID(1L)
                .build();

        when(service.addArtist(artist)).thenReturn(response);

        mockMvc.perform(post("/artists")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "name": "Maroon 5"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {
                                        "artistID": 1
                            }
                        """));

        verify(service).addArtist(artist);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void updateArtist() throws Exception{
        mockMvc.perform(put("/artists/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "name": "Maroon 5"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        ArtistDTO artist = ArtistDTO.builder()
                .id(1)
                .name("Maroon 5")
                .build();

        verify(service).editArtist(artist);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void deleteArtist() throws Exception{
        mockMvc.perform(delete("/artists/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).deleteArtist(1L);
    }
}
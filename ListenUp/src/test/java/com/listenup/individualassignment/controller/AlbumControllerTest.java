package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.album.*;
import com.listenup.individualassignment.dto.AlbumSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
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
import java.util.Date;
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
class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateAlbumUseCase createAlbumUseCase;
    @MockBean
    private GetAlbumsUseCase getAlbumsUseCase;
    @MockBean
    private GetAlbumSongsUseCase getAlbumSongsUseCase;
    @MockBean
    private DeleteAlbumUseCase deleteAlbumUseCase;
    @MockBean
    private UpdateAlbumUseCase updateAlbumUseCase;

    final Date date = new Date(2008,11,26);

    final ArtistDTO artist = ArtistDTO.builder()
            .id(1L)
            .name("Maroon 5")
            .build();

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllAlbums() throws Exception {
        AlbumDTO album1 = AlbumDTO.builder()
                .id(1L)
                .name("V")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();
        AlbumDTO album2 = AlbumDTO.builder()
                .id(2L)
                .name("Overexposed")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        List<AlbumDTO> albums = new ArrayList<>();
        albums.add(album1);
        albums.add(album2);

        when(getAlbumsUseCase.getAlbums()).thenReturn(albums);

        mockMvc.perform(get("/albums"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [
                                {
                                    "id": 1,
                                    "name": "V",
                                    "artist": {
                                        "id": 1,
                                        "name": "Maroon 5"
                                    },
                                    "releasedDate": "3908-12-25T23:00:00.000+00:00",
                                    "uploadedDate": "3908-12-25T23:00:00.000+00:00"
                                },
                                {
                                    "id": 2,
                                    "name": "Overexposed",
                                    "artist": {
                                        "id": 1,
                                        "name": "Maroon 5"
                                    },
                                    "releasedDate": "3908-12-25T23:00:00.000+00:00",
                                    "uploadedDate": "3908-12-25T23:00:00.000+00:00"
                                }
                            ]
                        """));

        verify(getAlbumsUseCase).getAlbums();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllAlbumsNotFound() throws Exception{
        when(getAlbumsUseCase.getAlbums()).thenReturn(null);

        mockMvc.perform(get("/albums"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getAlbumsUseCase).getAlbums();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAlbumPath() throws Exception{
        AlbumSongListDTO album = AlbumSongListDTO.builder()
                .id(1L)
                .name("V")
                .songs(Collections.emptyList())
                .build();

        when(getAlbumSongsUseCase.getAlbumSongs(1L)).thenReturn(album);

        mockMvc.perform(get("/albums/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                       "id": 1,
                                       "name": "V",
                                       "songs": []
                            }
                        """));

        verify(getAlbumSongsUseCase).getAlbumSongs(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAlbumPathNotFound() throws Exception{
        when(getAlbumSongsUseCase.getAlbumSongs(1L)).thenReturn(null);

        mockMvc.perform(get("/albums/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getAlbumSongsUseCase).getAlbumSongs(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void createAlbum() throws Exception {
        CreateAlbumRequestDTO album = CreateAlbumRequestDTO.builder()
                .name("Red Pills Blues")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        CreateAlbumResponseDTO response = CreateAlbumResponseDTO.builder()
                .albumID(1L)
                .build();

        when(createAlbumUseCase.addAlbum(album)).thenReturn(response);

        mockMvc.perform(post("/albums")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "name": "Red Pills Blues",
                                    "artist": {
                                        "id": 1,
                                        "name": "Maroon 5"
                                    },
                                    "releasedDate": "3908-12-25T23:00:00.000+00:00",
                                    "uploadedDate": "3908-12-25T23:00:00.000+00:00"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {
                                    "albumID": 1
                            }
                        """));

        verify(createAlbumUseCase).addAlbum(album);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void updateAlbum() throws Exception{
        mockMvc.perform(put("/albums/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "name": "Red Pills Blues",
                                    "artist": {
                                        "id": 1,
                                        "name": "Maroon 5"
                                    },
                                    "releasedDate": "3908-12-25T23:00:00.000+00:00",
                                    "uploadedDate": "3908-12-25T23:00:00.000+00:00"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        AlbumDTO album = AlbumDTO.builder()
                .id(1L)
                .name("Red Pills Blues")
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        verify(updateAlbumUseCase).editAlbum(album);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void deleteAlbum() throws Exception{
        mockMvc.perform(delete("/albums/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(deleteAlbumUseCase).deleteAlbum(1L);
    }
}
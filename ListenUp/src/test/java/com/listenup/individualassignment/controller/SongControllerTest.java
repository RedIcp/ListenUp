package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.SongService;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateAlbumSongResponseDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateSingleSongResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.AlbumDTO;
import com.listenup.individualassignment.dto.vieweditdto.ArtistDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
import com.listenup.individualassignment.dto.vieweditdto.SingleSongDTO;
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
class SongControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService service;

    Date date = new Date(2021,11,27);

    ArtistDTO artist = ArtistDTO.builder()
            .id(1L)
            .name("Maroon 5")
            .build();

    AlbumDTO album = AlbumDTO.builder()
            .id(1L)
            .name("V")
            .artist(artist)
            .releasedDate(date)
            .uploadedDate(date)
            .build();

    GenreDTO genre = GenreDTO.builder()
            .id(1L)
            .name("Pop")
            .build();

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllSongs() throws Exception{
        SingleSongDTO song = SingleSongDTO.builder()
                .id(1L)
                .name("Lost Stars")
                .genre(genre)
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        List<SingleSongDTO> songs = new ArrayList<>();
        songs.add(song);

        when(service.getSongs()).thenReturn(songs);

        mockMvc.perform(get("/songs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [
                                {
                                         "id": 1,
                                         "name": "Lost Stars",
                                         "artist": {
                                             "id": 1,
                                             "name": "Maroon 5"
                                         },
                                         "genre": {
                                             "id": 1,
                                             "name": "Pop"
                                         },
                                         "releasedDate": "3921-12-26T23:00:00.000+00:00",
                                         "uploadedDate": "3921-12-26T23:00:00.000+00:00"
                                     }
                            ]                          
                        """));

        verify(service).getSongs();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllSongsNotFound() throws Exception {
        when(service.getSongs()).thenReturn(null);

        mockMvc.perform(get("/songs"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getSongs();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getSongPath() throws Exception{
        SingleSongDTO song = SingleSongDTO.builder()
                .id(1L)
                .name("Lost Stars")
                .genre(genre)
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        when(service.getSong(1L)).thenReturn(song);

        mockMvc.perform(get("/songs/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                         "id": 1,
                                         "name": "Lost Stars",
                                         "artist": {
                                             "id": 1,
                                             "name": "Maroon 5"
                                         },
                                         "genre": {
                                             "id": 1,
                                             "name": "Pop"
                                         },
                                         "releasedDate": "3921-12-26T23:00:00.000+00:00",
                                         "uploadedDate": "3921-12-26T23:00:00.000+00:00"
                            }
                        """));

        verify(service).getSong(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getSongPathNotFound() throws Exception{
        when(service.getSong(1L)).thenReturn(null);

        mockMvc.perform(get("/songs/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getSong(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void createSingleSong() throws Exception{
        CreateSingleSongRequestDTO song = CreateSingleSongRequestDTO.builder()
                .name("Lost Stars")
                .artist(artist)
                .genre(genre)
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        CreateSingleSongResponseDTO response = CreateSingleSongResponseDTO.builder()
                .singleSongID(1L)
                .build();

        when(service.addSingleSong(song)).thenReturn(response);

        mockMvc.perform(post("/songs/singlesong")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                         "name": "Lost Stars",
                                         "artist": {
                                             "id": 1,
                                             "name": "Maroon 5"
                                         },
                                         "genre": {
                                             "id": 1,
                                             "name": "Pop"
                                         },
                                         "releasedDate": "3921-12-26T23:00:00.000+00:00",
                                         "uploadedDate": "3921-12-26T23:00:00.000+00:00"
                            }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {
                                         "singleSongID": 1
                            }
                        """));

        verify(service).addSingleSong(song);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void CreateAlbumSong() throws Exception{
        CreateAlbumSongRequestDTO song = CreateAlbumSongRequestDTO.builder()
                .name("Map")
                .album(album)
                .genre(genre)
                .build();

        CreateAlbumSongResponseDTO response = CreateAlbumSongResponseDTO.builder()
                .albumSongID(1L)
                .build();

        when(service.addAlbumSong(song)).thenReturn(response);

        mockMvc.perform(post("/songs/albumsong")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "name": "Map",
                                    "album": {
                                        "id": 1,
                                        "name": "V",
                                        "artist": {
                                            "id": 1,
                                            "name": "Maroon 5"
                                        },
                                        "releasedDate": "3921-12-26T23:00:00.000+00:00",
                                        "uploadedDate": "3921-12-26T23:00:00.000+00:00"
                                    },
                                    "genre": {
                                        "id": 1,
                                        "name": "Pop"
                                    }
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {
                                    "albumSongID": 1
                            }
                        """));

        verify(service).addAlbumSong(song);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void updateSong() throws Exception{
        mockMvc.perform(put("/songs/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                         "id": 1,
                                         "name": "Lost Stars",
                                         "artist": {
                                             "id": 1,
                                             "name": "Maroon 5"
                                         },
                                         "genre": {
                                             "id": 1,
                                             "name": "Pop"
                                         },
                                         "releasedDate": "3921-12-26T23:00:00.000+00:00",
                                         "uploadedDate": "3921-12-26T23:00:00.000+00:00"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        SingleSongDTO song = SingleSongDTO.builder()
                .id(1L)
                .name("Lost Stars")
                .artist(artist)
                .genre(genre)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        verify(service).editSong(song);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void deleteSong() throws Exception{
        mockMvc.perform(delete("/songs/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).deleteSong(1L);
    }
}
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@WebMvcTest(SongController.class)
class SongControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService service;

    Date date = new Date(2021,11,27);

    ArtistDTO artist = ArtistDTO.builder()
            .id(1l)
            .name("Maroon 5")
            .build();

    AlbumDTO album = AlbumDTO.builder()
            .id(1l)
            .name("V")
            .artist(artist)
            .releasedDate(date)
            .uploadedDate(date)
            .build();

    GenreDTO genre = GenreDTO.builder()
            .id(1l)
            .name("Pop")
            .build();

    @Test
    void getAllSongs() throws Exception{
        SingleSongDTO song = SingleSongDTO.builder()
                .id(1l)
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
    void getAllSongsNotFound() throws Exception {
        when(service.getSongs()).thenReturn(null);

        mockMvc.perform(get("/songs"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getSongs();
    }

    @Test
    void getSongPath() throws Exception{
        SingleSongDTO song = SingleSongDTO.builder()
                .id(1l)
                .name("Lost Stars")
                .genre(genre)
                .artist(artist)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        when(service.getSong(1l)).thenReturn(song);

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

        verify(service).getSong(1l);
    }

    @Test
    void getSongPathNotFound() throws Exception{
        when(service.getSong(1l)).thenReturn(null);

        mockMvc.perform(get("/songs/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service).getSong(1l);
    }

    @Test
    void createSingleSong() throws Exception{
        CreateSingleSongRequestDTO song = CreateSingleSongRequestDTO.builder()
                .name("Lost Stars")
                .artist(artist)
                .genre(genre)
                .releasedDate(date)
                .uploadedDate(date)
                .build();

        CreateSingleSongResponseDTO response = CreateSingleSongResponseDTO.builder()
                .singleSongID(1l)
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
    void CreateAlbumSong() throws Exception{
        CreateAlbumSongRequestDTO song = CreateAlbumSongRequestDTO.builder()
                .name("Map")
                .album(album)
                .genre(genre)
                .build();

        CreateAlbumSongResponseDTO response = CreateAlbumSongResponseDTO.builder()
                .albumSongID(1l)
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
                .id(1l)
                .name("Lost Stars")
                .artist(artist)
                .genre(genre)
                .uploadedDate(date)
                .releasedDate(date)
                .build();

        verify(service).editSong(song);
    }

    @Test
    void deleteSong() throws Exception{
        mockMvc.perform(delete("/songs/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).deleteSong(1L);
    }
}
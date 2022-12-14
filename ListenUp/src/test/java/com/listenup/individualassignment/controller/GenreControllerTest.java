package com.listenup.individualassignment.controller;

import com.listenup.individualassignment.business.genre.*;
import com.listenup.individualassignment.dto.GenreSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreRequestDTO;
import com.listenup.individualassignment.dto.createdto.CreateGenreResponseDTO;
import com.listenup.individualassignment.dto.vieweditdto.GenreDTO;
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
class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateGenreUseCase createGenreUseCase;
    @MockBean
    private GetGenresUseCase getGenresUseCase;
    @MockBean
    private GetGenreSongsUseCase getGenreSongsUseCase;
    @MockBean
    private DeleteGenreUseCase deleteGenreUseCase;
    @MockBean
    private UpdateGenreUseCase updateGenreUseCase;

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllGenres() throws  Exception{
        GenreDTO genre1 = GenreDTO.builder()
                .id(1L)
                .name("Pop")
                .build();
        GenreDTO genre2 = GenreDTO.builder()
                .id(2L)
                .name("Hip-hop")
                .build();

        List<GenreDTO> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        when(getGenresUseCase.getGenres()).thenReturn(genres);

        mockMvc.perform(get("/genres"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            [
                                {
                                    "id": 1,
                                    "name": "Pop"
                                },
                                {
                                    "id": 2,
                                    "name": "Hip-hop"
                                }
                            ]
                        """));

        verify(getGenresUseCase).getGenres();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getAllGenresNotFound() throws Exception {
        when(getGenresUseCase.getGenres()).thenReturn(null);

        mockMvc.perform(get("/genres"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getGenresUseCase).getGenres();
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getGenrePath() throws Exception{
        GenreSongListDTO genre = GenreSongListDTO.builder()
                .id(1L)
                .name("Pop")
                .songs(Collections.emptyList())
                .build();

        when(getGenreSongsUseCase.getGenreSongs(1L)).thenReturn(genre);

        mockMvc.perform(get("/genres/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {
                                       "id": 1,
                                       "name": "Pop",
                                       "songs": []
                            }
                        """));

        verify(getGenreSongsUseCase).getGenreSongs(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void getGenrePathNotFound() throws Exception{
        when(getGenreSongsUseCase.getGenreSongs(1L)).thenReturn(null);

        mockMvc.perform(get("/genres/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getGenreSongsUseCase).getGenreSongs(1L);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void createGenre() throws Exception{
        CreateGenreRequestDTO genre = CreateGenreRequestDTO.builder()
                .name("Pop")
                .build();

        CreateGenreResponseDTO response = CreateGenreResponseDTO.builder()
                .genreID(1L)
                .build();

        when(createGenreUseCase.addGenre(genre)).thenReturn(response);

        mockMvc.perform(post("/genres")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "name": "Pop"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            {
                                        "genreID": 1
                            }
                        """));

        verify(createGenreUseCase).addGenre(genre);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void updateGenre() throws Exception{
        mockMvc.perform(put("/genres/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                        "id": 1,
                                        "name": "Pop"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isNoContent());

        GenreDTO genre = GenreDTO.builder()
                .id(1)
                .name("Pop")
                .build();

        verify(updateGenreUseCase).editGenre(genre);
    }

    @Test
    @WithMockUser(username = "Yellow", roles = {"ADMIN"})
    void deleteGenre() throws Exception{
        mockMvc.perform(delete("/genres/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(deleteGenreUseCase).deleteGenre(1L);
    }
}
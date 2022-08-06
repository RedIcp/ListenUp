package com.listenup.individualassignment.business.imp.dtoconverter;

import com.listenup.individualassignment.business.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.dtoconverter.PlaylistDTOConverter;
import com.listenup.individualassignment.dto.PlaylistSongListDTO;
import com.listenup.individualassignment.dto.createdto.CreatePlaylistRequestDTO;
import com.listenup.individualassignment.dto.vieweditdto.PlaylistDTO;
import com.listenup.individualassignment.repository.entity.Customer;
import com.listenup.individualassignment.repository.entity.Playlist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistDTOConverterTest {
    final Customer customer = new Customer(1L, "Yellow", "yellow@gmail.com", "123Yellow");

    final Playlist expectedModel = Playlist.builder()
            .id(1L)
            .name("Chill")
            .customer(customer)
            .songs(Collections.emptyList())
            .build();

    final PlaylistDTO expectedDTO = PlaylistDTO.builder()
            .id(1L)
            .name("Chill")
            .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
            .build();

    @Test
    void convertToDTO() {
        PlaylistDTO actualDTO = PlaylistDTOConverter.convertToDTO(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToModelForUpdate() {
        Playlist actualModel = PlaylistDTOConverter.convertToModelForUpdate(expectedDTO);
        actualModel.setSongs(Collections.emptyList());

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToModelForCreate() {
        CreatePlaylistRequestDTO dto = CreatePlaylistRequestDTO.builder()
                .name("Chill")
                .customer(CustomerDTOConverter.convertToDTOForUpdate(customer))
                .build();

        Playlist actualModel = PlaylistDTOConverter.convertToModelForCreate(dto);
        actualModel.setSongs(Collections.emptyList());
        actualModel.setId(1L);

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToDTOForSong() {
        PlaylistSongListDTO expectedDTO = PlaylistSongListDTO.builder()
                .id(1L)
                .customer("Yellow")
                .name("Chill")
                .songs(Collections.emptyList())
                .build();

        PlaylistSongListDTO actualDTO = PlaylistDTOConverter.convertToDTOForSong(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToDTOList() {
        Playlist playlist1 = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .songs(Collections.emptyList())
                .build();
        Playlist playlist2 = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .songs(Collections.emptyList())
                .build();

        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist1);
        playlists.add(playlist2);

        List<PlaylistDTO> expectedList = new ArrayList<>();
        expectedList.add(PlaylistDTOConverter.convertToDTO(playlist1));
        expectedList.add(PlaylistDTOConverter.convertToDTO(playlist2));

        List<PlaylistDTO> actualList = PlaylistDTOConverter.convertToDTOList(playlists);

        assertEquals(actualList, expectedList);
    }

    @Test
    void convertToModelList() {
        Playlist playlist1 = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .songs(Collections.emptyList())
                .build();
        Playlist playlist2 = Playlist.builder()
                .id(1L)
                .name("Chill")
                .customer(customer)
                .songs(Collections.emptyList())
                .build();

        List<Playlist> expectedList = new ArrayList<>();
        expectedList.add(playlist1);
        expectedList.add(playlist2);

        List<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(PlaylistDTOConverter.convertToDTO(playlist1));
        playlists.add(PlaylistDTOConverter.convertToDTO(playlist2));

        List<Playlist> actualPlaylists = PlaylistDTOConverter.convertToModelList(playlists);

        assertEquals(actualPlaylists.size(), expectedList.size());
    }
}
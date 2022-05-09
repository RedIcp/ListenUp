package com.listenup.individualassignment.business.imp.dtoconverter;

import com.listenup.individualassignment.dto.CustomerLikedPlaylistListDTO;
import com.listenup.individualassignment.dto.CustomerLikedSongListDTO;
import com.listenup.individualassignment.dto.CustomerPlaylistListDTO;
import com.listenup.individualassignment.dto.createdto.CreateUserDTO;
import com.listenup.individualassignment.dto.vieweditdto.UserDTO;
import com.listenup.individualassignment.model.Admin;
import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOConverterTest {
    Customer expectedModel = new Customer(1l, "Yellow", "yellow@gmail.com", "123Yellow");

    UserDTO expectedDTO = UserDTO.builder()
            .id(1l)
            .username("Yellow")
            .email("yellow@gmail.com")
            .password("123Yellow")
            .build();

    @Test
    void convertToDTO() {
        UserDTO actualDTO = CustomerDTOConverter.convertToDTO(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToModelForUpdate() {
        Customer actualModel = CustomerDTOConverter.convertToModelForUpdate(expectedDTO);

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToModelForCreate() {
        CreateUserDTO dto = CreateUserDTO.builder()
                .username("Yellow")
                .email("yellow@gmail.com")
                .password("123Yellow")
                .build();

        Customer actualModel = CustomerDTOConverter.convertToModelForCreate(dto);
        actualModel.setLikedPlaylists(Collections.emptyList());
        actualModel.setLikedSongs(Collections.emptyList());
        actualModel.setPlaylists(Collections.emptyList());
        actualModel.setId(1l);

        assertEquals(actualModel, expectedModel);
    }

    @Test
    void convertToDTOForLikedSong() {
        CustomerLikedSongListDTO expectedDTO = CustomerLikedSongListDTO.builder()
                .id(1l)
                .likedSongs(Collections.emptyList())
                .build();

        CustomerLikedSongListDTO actualDTO = CustomerDTOConverter.convertToDTOForLikedSong(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToDTOForPlaylist() {
        CustomerPlaylistListDTO expectedDTO = CustomerPlaylistListDTO.builder()
                .id(1l)
                .playlists(Collections.emptyList())
                .build();

        CustomerPlaylistListDTO actualDTO = CustomerDTOConverter.convertToDTOForPlaylist(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToDTOForLikedPlaylist() {
        CustomerLikedPlaylistListDTO expectedDTO = CustomerLikedPlaylistListDTO.builder()
                .id(1l)
                .likedPlaylists(Collections.emptyList())
                .build();

        CustomerLikedPlaylistListDTO actualDTO = CustomerDTOConverter.convertToDTOForLikedPlaylist(expectedModel);

        assertEquals(actualDTO, expectedDTO);
    }

    @Test
    void convertToDTOList() {
        Customer user1 = new Customer(1l, "Yellow", "yellow@gmail.com", "123Yellow");
        Admin user2 = new Admin(2l, "Red", "red@gmail.com", "123Red");
        Admin user3 = new Admin("Ash", "ash@gmail.com", "123Ash");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        List<UserDTO> expectedList = new ArrayList<>();
        expectedList.add(CustomerDTOConverter.convertToDTO(user1));
        expectedList.add(CustomerDTOConverter.convertToDTO(user2));

        List<UserDTO> actualList = CustomerDTOConverter.convertToDTOList(users);

        assertEquals(actualList, expectedList);
    }
}
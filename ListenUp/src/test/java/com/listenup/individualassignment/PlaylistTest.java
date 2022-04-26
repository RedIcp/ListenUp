package com.listenup.individualassignment;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import com.listenup.individualassignment.business.imp.dtoconverter.CustomerDTOConverter;
import com.listenup.individualassignment.business.imp.dtoconverter.PlaylistDTOConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.model.Playlist;
import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.business.PlaylistService;
import com.listenup.individualassignment.repository.UserRepository;
import com.listenup.individualassignment.business.imp.UserServiceImp;
import com.listenup.individualassignment.dto.createupdate.PlaylistDTO;
import com.listenup.individualassignment.repository.PlaylistRepository;
import com.listenup.individualassignment.business.imp.PlaylistServiceImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PlaylistTest {
    @Autowired
    PlaylistRepository dbPlaylist;
    @Autowired
    UserRepository dbUser;

    PlaylistService playlistMG;
    UserService userMG;

    @BeforeEach
    void  setUp(){
        playlistMG = new PlaylistServiceImp(dbPlaylist);
        userMG = new UserServiceImp(dbUser);
    }
    //dto: valid input
    @Test
    void playlistDTOConvertorValidInput() {
        Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(customer);
        Playlist playlist = new Playlist(1, "Workout", customer);
        playlistMG.addPlaylist(playlist);

        PlaylistDTO playlistDTO= new PlaylistDTO(1, "Workout", CustomerDTOConverter.convertToDTO(customer));
        assertNotNull(PlaylistDTOConverter.convertToModel(playlistDTO));
    }
    @Test
    void playlistObjConvertorValidInput() {
        Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(customer);
        Playlist playlist = new Playlist(1, "Workout", customer);
        playlistMG.addPlaylist(playlist);

        assertNotNull(PlaylistDTOConverter.convertToDTO(playlist));
    }
    @Test
    void playlistObjConvertorForSongValidInput() {
        Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(customer);
        Playlist playlist = new Playlist(1, "Workout", customer);
        playlistMG.addPlaylist(playlist);

        assertNotNull(PlaylistDTOConverter.convertToDTOForSong(playlist));
    }
    @Test
    void playlistDTOListConvertorValidInput() {
        Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        Playlist playlist = new Playlist(1, "Workout", customer);
        List<Playlist> playlistList = new ArrayList<>();
        playlistList.add(playlist);

        assertNotNull(PlaylistDTOConverter.convertToDTOList(playlistList));
    }

    //create playlist: valid input
    @Test
    void addPlaylistValidInput() {
        Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(customer);
        Playlist playlist = new Playlist(1, "Workout", customer);
        Playlist playlist1 = new Playlist(2, "Chill", customer);
        playlistMG.addPlaylist(playlist1);

        assertTrue(playlistMG.addPlaylist(playlist));
    }
    //create playlist: same id
    @Test
    void addPlaylistSameID() {
        Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(customer);
        Playlist playlist = new Playlist(1, "Workout", customer);
        Playlist playlist1 = new Playlist(1, "Chill", customer);
        playlistMG.addPlaylist(playlist);
        assertFalse(playlistMG.addPlaylist(playlist1));
    }

    //update playlist: valid input
    @Test
    void updatePlaylistValidInput() {
        Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(customer);
        Playlist playlist1 = new Playlist(1, "Workout", customer);
        Playlist playlist2 = new Playlist(1,"Chill", customer);
        playlistMG.addPlaylist(playlist1);
        assertTrue(playlistMG.editPlaylist(playlist2));
    }
    //update playlist: playlist not in database
    @Test
    void updatePlaylistInvalidID() {
        Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(customer);
        Playlist playlist = new Playlist(1, "Workout", customer);
        assertFalse(playlistMG.editPlaylist(playlist));
    }

    //delete playlist: valid input
    @Test
    void deletePlaylistValidInput() {
        Customer customer = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(customer);
        Playlist playlist = new Playlist(1, "Workout", customer);
        playlistMG.addPlaylist(playlist);
        assertTrue(playlistMG.deletePlaylist(1));
    }

    //delete playlist: invalid id
    @Test
    void deletePlaylistInvalidID() {
        assertFalse(playlistMG.deletePlaylist(100));
    }
}

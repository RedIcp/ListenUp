package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    void save() {
        Customer customer = new Customer("Yellow", "yellow@gmail.com", "123Yellow");

        Customer actualUser = repository.save(customer);

        Customer expectedUser = new Customer(actualUser.getId(), "Yellow", "yellow@gmail.com", "123Yellow");

        assertEquals(actualUser.getUsername(), expectedUser.getUsername());
    }

    @Test
    void existsByEmail() {
        Customer customer = new Customer("Yellow", "yellow@gmail.com", "123Yellow");
        entityManager.persist(customer);

        boolean actual = repository.existsByEmail("yellow@gmail.com");

        assertTrue(actual);
    }

    @Test
    void getAllPlaylists(){
        Customer customer = new Customer("Yellow", "yellow@gmail.com", "123Yellow");
        entityManager.persist(customer);

        Playlist playlist = Playlist.builder()
                .name("Chill")
                .customer(customer)
                .build();
        entityManager.persist(playlist);

        entityManager.flush();
        entityManager.clear();

        Customer savedCustomer = entityManager.find(Customer.class, customer.getId());

        assertEquals(List.of(playlist).size(), savedCustomer.getPlaylists().size());
    }

    @Test
    void getAllLikedPlaylists(){
        Customer customer1 = new Customer("Yellow", "yellow@gmail.com", "123Yellow");
        entityManager.persist(customer1);

        Playlist playlist = Playlist.builder()
                .name("Chill")
                .customer(customer1)
                .build();
        entityManager.persist(playlist);

        Customer customer2 = new Customer("Blue", "blue@gmail.com", "123Blue");
        customer2.setLikedPlaylists(List.of(playlist));
        entityManager.persist(customer2);

        entityManager.flush();
        entityManager.clear();

        Customer savedCustomer = entityManager.find(Customer.class, customer2.getId());

        assertEquals(List.of(playlist).size(), savedCustomer.getLikedPlaylists().size());
    }

    @Test
    void getAllLikedSong(){
        Artist artist = Artist.builder()
                .name("Maroon 5")
                .build();
        entityManager.persist(artist);

        Genre genre = Genre.builder()
                .name("Pop")
                .build();
        entityManager.persist(genre);

        Date date = new Date(2021,11,27);

        Song song = new SingleSong("Map", artist, genre, date, date);
        entityManager.persist(song);

        Customer customer = new Customer("Blue", "blue@gmail.com", "123Blue");
        customer.setLikedSongs(List.of(song));
        entityManager.persist(customer);

        entityManager.flush();
        entityManager.clear();

        Customer savedCustomer = entityManager.find(Customer.class, customer.getId());

        assertEquals(List.of(song).size(), savedCustomer.getLikedSongs().size());
    }

    @Test
    void getAllUsers(){
        Customer customer = new Customer("Yellow", "yellow@gmail.com", "123Yellow");
        entityManager.persist(customer);

        List<User> expectedList = new ArrayList<>();
        expectedList.add(customer);

        List<User> actualList = repository.findAll();

        assertEquals(actualList, expectedList);
    }
}
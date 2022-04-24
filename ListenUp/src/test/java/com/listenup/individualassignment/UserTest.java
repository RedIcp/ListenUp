package com.listenup.individualassignment;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.listenup.individualassignment.model.User;
import com.listenup.individualassignment.model.Admin;
import com.listenup.individualassignment.model.Customer;
import com.listenup.individualassignment.business.UserService;
import com.listenup.individualassignment.dto.createupdate.UserDTO;
import com.listenup.individualassignment.repository.UserRepository;
import com.listenup.individualassignment.business.imp.UserServiceImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserTest {
    @Autowired
    UserRepository dbUser;

    UserService userMG;

    @BeforeEach
    void  setUp(){
        userMG = new UserServiceImp(dbUser);
    }
    //dto: valid input
    @Test
    void userDTOConvertorValidInput() {
        User user = new Admin(10, "Jam", "jam@yahoo.com", "123Jam");
        UserDTO userDTO = new UserDTO(10, "Jam", "jam@yahoo.com", "123Jam");
        userMG.createAccount(user);
        assertNotNull(userMG.userDTOConvertor(userDTO));
    }
    @Test
    void userObjConvertorValidInput() {
        User user = new Admin(10, "Jam", "jam@yahoo.com", "123Jam");
        userMG.createAccount(user);
        assertNotNull(userMG.userObjConvertorForProfile(user));
    }
    @Test
    void userObjConvertorForPlaylistsValidInput() {
        User user = new Customer(10, "Jam", "jam@yahoo.com", "123Jam");
        userMG.createAccount(user);
        assertNotNull(userMG.customerObjConvertorForPlaylists((Customer) user));
    }
    @Test
    void userObjConvertorForLikedSongsValidInput() {
        User user = new Customer(10, "Jam", "jam@yahoo.com", "123Jam");
        userMG.createAccount(user);
        assertNotNull(userMG.customerObjConvertorForLikedSongs((Customer) user));
    }
    //dto list: valid input
    @Test
    void userDTOListConvertorValidInput() {
        User user = new Customer(10, "Jam", "jam@yahoo.com", "123Jam");
        List<User> users = new ArrayList<>();
        users.add(user);
        assertNotNull(userMG.getUserDTOs(users));
    }
    //login: valid input
    @Test
    void loginValidInput() {
        User user = new Admin(10, "Jam", "jam@yahoo.com", "123Jam");
        userMG.createAccount(user);
        assertTrue(userMG.login(user));
    }
    //login: invalid input
    @Test
    void loginInvalidInput() {
        User user1 = new Admin(10, "Jam", "jam@yahoo.com", "123Jam");
        User user2 = new Admin(2, "Jam", "jam@yahoo.com", "1234");
        userMG.createAccount(user1);
        assertFalse(userMG.login(user2));
    }
    //create user: valid input
    @Test
    void createUserValidInput() {
        User user = new Admin(10, "Jam", "jam@yahoo.com", "123Jam");
        assertTrue(userMG.createAccount(user));
    }
    //create user: same email
    @Test
    void createUserSameEmail() {
        User user1 = new Admin(10, "Jam", "yellow@gmail.com", "123Jam");
        User user2 = new Customer(1, "Jam", "yellow@gmail.com", "123Jam");
        userMG.createAccount(user1);
        assertFalse(userMG.createAccount(user2));
    }
    //create user: same id
    @Test
    void createUserSameID() {
        User user1 = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        User user2 = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(user1);
        assertFalse(userMG.createAccount(user2));
    }

    //update user: valid input
    @Test
    void updateUserValidInput() {
        User user1 = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        User user2 = new Customer(1, "Yellow", "yellow@gmail.com", "123Yellow");
        userMG.createAccount(user1);
        assertTrue(userMG.updateAccount(user2));
    }
    //update user: same email same user
    @Test
    void updateUserSameEmailAsUser() {
        User user1 = new Customer(1, "Jam", "yellow@gmail.com", "123Jam");
        User user2 = new Customer(1, "Yellow", "yellow@gmail.com", "123Yellow");
        userMG.createAccount(user1);
        assertTrue(userMG.updateAccount(user2));
    }
    //update user: same email another user
    @Test
    void updateUserSameEmailAsAnotherUser() {
        User user1 = new Customer(1, "Red", "red@gmail.com", "123Red");
        User user2 = new Customer(2, "Yellow", "yellow@gmail.com", "123Yellow");
        User user3 = new Customer(2, "Jam", "red@gmail.com", "123Jam");
        userMG.createAccount(user1);
        userMG.createAccount(user2);
        assertFalse(userMG.updateAccount(user3));
    }

    //delete user: valid input
    @Test
    void deleteUserValidInput() {
        User user = new Customer(10, "Jam", "jam@gmail.com", "123Jam");
        userMG.createAccount(user);
        assertTrue(userMG.deleteAccount(10));
    }

    //delete user: invalid id
    @Test
    void deleteUserInvalidID() {
        assertFalse(userMG.deleteAccount(100));
    }
}

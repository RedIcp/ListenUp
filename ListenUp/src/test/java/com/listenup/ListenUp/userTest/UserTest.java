package com.listenup.ListenUp.userTest;

import com.listenup.ListenUp.business.UserManagment;
import com.listenup.ListenUp.business.imp.UserManagmentImp;
import com.listenup.ListenUp.model.Customer;
import com.listenup.ListenUp.model.User;
import com.listenup.ListenUp.persistence.DBUser;
import com.listenup.ListenUp.persistence.imp.DBUserImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    DBUser db;
    UserManagment managment;

    @BeforeEach
    void  setUp(){
        db = new DBUserImp();
        managment = new UserManagmentImp(db);
    }

    @Test
    void createNewUser1() {
        User user = new Customer(10, "Jam", "jam@yahoo.com", "123Jam");
        assertTrue(managment.createAccount(user));
    }
    @Test
    void createNewUser2() {
        User user = new Customer(10, "Jam", "jam@yahoo.com", "123Jam");
        managment.createAccount(user);
        assertEquals(7, managment.getUsers().stream().count());
    }

    //same email
    @Test
    void createNewUser3() {
        User user = new Customer(10, "Jam", "yellow@gmail.com", "123Jam");
        assertFalse(managment.createAccount(user));
    }
    //same id
    @Test
    void createNewUser4() {
        User user = new Customer(1, "Jam", "jam@gmail.com", "123Jam");
        assertFalse(managment.createAccount(user));
    }

    @Test
    void deleteNewUser1() {
        User user = new Customer(10, "Jam", "jam@gmail.com", "123Jam");
        managment.createAccount(user);
        assertTrue(managment.deleteAccount(10));
    }

    //invalid id
    @Test
    void deleteNewUser2() {
        User user = new Customer(10, "Jam", "jam@gmail.com", "123Jam");
        managment.createAccount(user);
        assertFalse(managment.deleteAccount(100));
    }
}

package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.entity.Customer;
import com.listenup.individualassignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User getByEmail(String email);
    Customer getUserById(long id);
}

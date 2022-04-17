package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

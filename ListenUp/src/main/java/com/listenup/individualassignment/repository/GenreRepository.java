package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}

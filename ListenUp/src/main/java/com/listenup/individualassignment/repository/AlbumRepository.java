package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbumRepository extends JpaRepository<Album, Long> {

}

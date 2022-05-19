package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbumRepository extends JpaRepository<Album, Long> {

}

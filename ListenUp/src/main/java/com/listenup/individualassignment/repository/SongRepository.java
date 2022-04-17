package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {

}

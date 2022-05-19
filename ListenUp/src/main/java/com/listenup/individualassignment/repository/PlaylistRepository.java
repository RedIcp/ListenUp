package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}

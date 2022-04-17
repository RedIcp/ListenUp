package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}

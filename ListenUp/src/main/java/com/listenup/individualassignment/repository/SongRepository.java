package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.repository.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Query(
            value =
                    "select exists (select * from liked_song where song_id = :songID AND customer_id = :customerID)",
            nativeQuery = true)
    int songLiked(@Param("songID") long songID, @Param("customerID") long customerID);
}

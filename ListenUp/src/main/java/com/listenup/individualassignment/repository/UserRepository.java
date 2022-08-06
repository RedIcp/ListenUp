package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.repository.entity.Customer;
import com.listenup.individualassignment.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User getByEmail(String email);
    Customer getUserById(long id);

    @Modifying
    @Query(
            value =
                    "delete from liked_song where song_id = :songID and customer_id = :customerID",
            nativeQuery = true)
    void removeLikedSong(@Param("songID") long songID, @Param("customerID") long customerID);

    @Modifying
    @Query(
            value =
                    "delete from liked_playlist where playlist_id = :PlaylistID and customer_id = :CustomerID",
            nativeQuery = true)
    void removeLikedPlaylist(@Param("PlaylistID") long playlistID, @Param("CustomerID") long customerID);

}

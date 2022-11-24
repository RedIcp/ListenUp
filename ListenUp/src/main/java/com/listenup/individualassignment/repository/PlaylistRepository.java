package com.listenup.individualassignment.repository;

import com.listenup.individualassignment.repository.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    @Query(
            value =
                    "select exists (select * from liked_playlist where playlist_id = :playlistID and customer_id = :customerID)",
            nativeQuery = true)
    int playlistLiked(@Param("playlistID") long playlistID, @Param("customerID") long customerID);

    @Query(
            value =
                    "select exists (select * from playlist_song where playlist_id = :playlistID and song_id = :songID)",
            nativeQuery = true)
    int songExistInPlaylist(@Param("playlistID") long playlistID, @Param("songID") long songID);

    @Modifying
    @Query(
            value =
                    "delete from playlist_song where song_id = :songID and playlist_id = :playlistID",
            nativeQuery = true)
    void removePlaylistSong(@Param("songID") long songID, @Param("playlistID") long playlistID);
}

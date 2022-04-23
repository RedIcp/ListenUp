package com.listenup.individualassignment.model;

import lombok.*;
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer extends User{
    @ManyToMany
    @JoinTable(
            name = "liked_song",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> likedSongs;

    @OneToMany
    @JoinTable(
            name = "playlist",
            joinColumns = @JoinColumn(name = "customer_id"))
    private List<Playlist> playlists;

    @ManyToMany
    @JoinTable(
            name = "liked_playlist",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<Playlist> likedPlaylists;

    public Customer(long id, String username, String email, String password){
        super(id, username, email, password);

        this.likedSongs = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.likedPlaylists = new ArrayList<>();
    }
}

package com.listenup.individualassignment.entity;

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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "liked_song",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> likedSongs;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Playlist> playlists;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "liked_playlist",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<Playlist> likedPlaylists;

    public Customer(String username, String email, String password){
        super(username, email, password);

        this.likedSongs = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.likedPlaylists = new ArrayList<>();
    }
    public Customer(long id, String username, String email, String password){
        super(id, username, email, password);

        this.likedSongs = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.likedPlaylists = new ArrayList<>();
    }
}

package com.listenup.individualassignment.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import javax.persistence.*;

@Builder
@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User{
    @ManyToMany
    @JoinTable(
            name = "liked_song",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> liked_songs;

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
    private List<Playlist> liked_playlists;

    public Customer(int id, String username, String email, String password){
        super(id, username, email, password);

        this.liked_songs = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.liked_playlists = new ArrayList<>();
    }
}

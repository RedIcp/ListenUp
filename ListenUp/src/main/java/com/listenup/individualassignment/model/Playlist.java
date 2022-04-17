package com.listenup.individualassignment.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Table(name = "playlist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    @Id
    @Column(name = "id")
    private int id;

    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @Column(name = "is_public")
    private boolean is_public;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    @Column(name = "liked_users")
    private int liked_users;

    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;

    public Playlist(int id, String name, Customer owner){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.is_public = true;
        this.liked_users = 0;

        this.songs = new ArrayList<>();
    }


}

package com.listenup.individualassignment.model;

import lombok.*;
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "playlist")
public class Playlist {
    @Id
    @Column(name = "id")
    private long id;

    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @Column(name = "is_public")
    private boolean isPublic;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    @Column(name = "liked_users")
    private long likedUsers;

    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;

    public Playlist(long id, String name, Customer owner){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.isPublic = true;
        this.likedUsers = 0;

        this.songs = new ArrayList<>();
    }
}

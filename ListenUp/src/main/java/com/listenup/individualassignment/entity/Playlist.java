package com.listenup.individualassignment.entity;

import lombok.*;
import java.util.List;
import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @Column(name = "is_public")
    private boolean isPublic;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    @Column(name = "liked_users")
    private long likedUsers;

    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;
}

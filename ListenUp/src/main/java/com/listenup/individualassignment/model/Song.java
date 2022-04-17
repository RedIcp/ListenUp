package com.listenup.individualassignment.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "song")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Song{
    @Id
    @Column(name = "id")
    private int id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "released_date")
    private Date released_date;

    @Column(name = "uploaded_date")
    private Date uploaded_date;

    /*@Column(name = "viewers")
    private int viewers;
    @Column(name = "liked_users")
    private int liked_users;*/

    @ManyToMany
    @JoinTable(
            name = "featured_artist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> features;

    protected Song(int id, String name, Artist artist, Genre genre, Date released_date, Date uploaded_date){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.released_date = released_date;
        this.uploaded_date = uploaded_date;

        this.features = new ArrayList<>();
    }
}

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
    private long id;

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
    private Date releasedDate;

    @Column(name = "uploaded_date")
    private Date uploadedDate;


    @ManyToMany
    @JoinTable(
            name = "featured_artist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> features;

    protected Song(long id, String name, Artist artist, Genre genre, Date releasedDate, Date uploadedDate){
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.releasedDate = releasedDate;
        this.uploadedDate = uploadedDate;


        this.features = new ArrayList<>();
    }
}

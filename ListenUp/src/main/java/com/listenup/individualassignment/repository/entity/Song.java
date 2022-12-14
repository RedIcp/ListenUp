package com.listenup.individualassignment.repository.entity;

import lombok.*;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "song")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Song{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "genre_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Genre genre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "artist_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
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

    protected Song(String name, Artist artist, Genre genre, Date releasedDate, Date uploadedDate){
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.releasedDate = releasedDate;
        this.uploadedDate = uploadedDate;

        this.features = new ArrayList<>();
    }
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
